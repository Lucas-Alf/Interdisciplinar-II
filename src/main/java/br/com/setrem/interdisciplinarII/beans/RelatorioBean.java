/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.FiltroRelatorio;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.repository.FiltroRelatorioRepository;
import br.com.setrem.interdisciplinarII.repository.RelatorioRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Named(value = "relatorioBean")
@SessionScoped
public class RelatorioBean implements Serializable {

    @Autowired
    private RelatorioRepository relatorioRepository;
    @Autowired
    private FiltroRelatorioRepository filtroRelatorioRepository;

    private String descricaoBusca = "";
    private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorio = new ArrayList<FiltroRelatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorioSelecionados = new ArrayList<FiltroRelatorio>();
    private Connection conexao = null;

    public String getDescricaoBusca() {
        return descricaoBusca;
    }

    public void setDescricaoBusca(String descricaoBusca) {
        this.descricaoBusca = descricaoBusca;
    }

    public List<Relatorio> getListaRelatorio() {
        return listaRelatorio;
    }

    public void setListaRelatorio(List<Relatorio> listaRelatorio) {
        this.listaRelatorio = listaRelatorio;
    }

    public RelatorioBean() {
    }

    public void Lista() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.listaRelatorio = relatorioRepository.Lista(this.descricaoBusca, empresa.getId());
    }

    public List<FiltroRelatorio> getListaFiltroRelatorio() {
        return listaFiltroRelatorio;
    }

    public void setListaFiltroRelatorio(List<FiltroRelatorio> listaFiltroRelatorio) {
        this.listaFiltroRelatorio = listaFiltroRelatorio;
    }

    public List<FiltroRelatorio> getListaFiltroRelatorioSelecionados() {
        return listaFiltroRelatorioSelecionados;
    }

    public void setListaFiltroRelatorioSelecionados(List<FiltroRelatorio> listaFiltroRelatorioSelecionados) {
        this.listaFiltroRelatorioSelecionados = listaFiltroRelatorioSelecionados;
    }

    public void AbreImpressao(int relatorioId) {
        if (relatorioId == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um relatório para imprimir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            this.listaFiltroRelatorio = filtroRelatorioRepository.listar(relatorioId);
            if (listaFiltroRelatorio.size() == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                        "Nenhum filtro encontrado para o relatório.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                this.listaFiltroRelatorioSelecionados = new ArrayList<FiltroRelatorio>();
                ImprimirRelatorio(relatorioId);
            } else {
                PrimeFaces.current().executeScript("$('#impressaoModal').modal('show');");
            }
        }
    }

    public void ImprimirRelatorio(int relatorioId) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(relatorioId);
        if (relatorio.isPresent()) {

            // Abre conexão com o banco
            try {
                String url = "jdbc:postgresql://ec2-50-19-95-77.compute-1.amazonaws.com:5432/d43v5g6nfkagp";
                String usuario = "pnbqtudcpohwgn";
                String senha = "c3265949766568e312fdc45e956de4aa8eec60121034cf0871821674bce06027";
                this.conexao = DriverManager.getConnection(url, usuario, senha);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                        "Não foi possível abrir uma conexão com o banco de dados!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                System.err.println(e.getMessage());
            }

            // JASPER
            try {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                InputStream reportStream = facesContext.getExternalContext()
                        .getResourceAsStream("/reports/" + relatorio.get().getNome() + ".jasper");
                ServletOutputStream outputStream = response.getOutputStream();
                Map<String, Object> parametros = new HashMap<String, Object>();
                JasperRunManager.runReportToPdfStream(reportStream, outputStream, parametros, this.conexao);
                response.setContentType("application/pdf");
                facesContext.responseComplete();
                outputStream.flush();
                outputStream.close();
                facesContext.renderResponse();

            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                        "Ocorreu um erro ao imprimir o relatório!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                System.err.println(e.getMessage());
            } finally {
                try {
                    // Fecha a conexão com o banco
                    this.conexao.close();
                } catch (Exception e) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                            "Não foi possível fechar a conexão com o banco de dados!");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, fm);
                    System.err.println(e.getMessage());
                }
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Relatório não encontrado!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }
}