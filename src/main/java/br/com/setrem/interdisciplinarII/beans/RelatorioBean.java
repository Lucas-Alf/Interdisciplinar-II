/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.FiltroRelatorio;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.repository.FiltroRelatorioRepository;
import br.com.setrem.interdisciplinarII.repository.RelatorioRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Named(value = "relatorioBean")
@SessionScoped
public class RelatorioBean implements Serializable {

    @Autowired
    private RelatorioRepository relatorioRepository;
    @Autowired
    private FiltroRelatorioRepository filtroRelatorioRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    private String descricaoBusca = "";
    private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorio = new ArrayList<FiltroRelatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorioSelecionados = new ArrayList<FiltroRelatorio>();

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

    public void lista() {
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

    public void abreImpressao(int relatorioId) {
        if (relatorioId == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um relatório para imprimir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            this.listaFiltroRelatorio = filtroRelatorioRepository.listar(relatorioId);
            if (listaFiltroRelatorio.size() == 0) {
                this.listaFiltroRelatorioSelecionados = new ArrayList<FiltroRelatorio>();
            }
            PrimeFaces.current().executeScript("$('#impressaoModal').modal('show');");
        }
    }

    private Connection abreConexao() {
        try {
            String url = "jdbc:postgresql://ec2-50-19-95-77.compute-1.amazonaws.com:5432/d43v5g6nfkagp";
            String usuario = "pnbqtudcpohwgn";
            String senha = "c3265949766568e312fdc45e956de4aa8eec60121034cf0871821674bce06027";
            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    "Não foi possível abrir uma conexão com o banco de dados!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void fechaConexao(Connection conexao) {
        try {
            conexao.close();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    "Não foi possível fechar a conexão com o banco de dados!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            System.err.println(e.getMessage());
        }
    }

    private String gerarConsulta(Relatorio relatorio, List<FiltroRelatorio> filtrosList) {
        String sql = relatorio.getSqlquery();
        FiltroRelatorio[] filtros = filtrosList.toArray(new FiltroRelatorio[filtrosList.size()]);
        if (filtros.length > 0) {
            sql += " WHERE ";
            for (int i = 0; i < filtros.length; i++) {
                if (i == 0) {
                    sql += filtros[i].getSqlwhere();
                } else {
                    sql += (" AND " + filtros[i].getSqlwhere());
                }
            }
        }
        return sql;
    }

    private JRResultSetDataSource gerarDataSource(String consulta) {
        Connection conexao = abreConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(consulta);
            ResultSet result = statement.executeQuery();
            return new JRResultSetDataSource(result);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    "Não foi possível gerar o ResultSet para o relatório!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            System.err.println(e.getMessage());
            return null;
        } finally {
            fechaConexao(conexao);
        }
    }

    private JasperPrint exportPdfFile(Relatorio relatorio) {
        try {
            Resource resource = resourceLoader
                    .getResource("classpath:/META-INF/resources/reports/" + relatorio.getNome() + ".jrxml");
            URI uri = resource.getURI();
            String path = uri.getPath();
            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameters = new HashMap<String, Object>();
            // JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
            // this.conexao);
            JRResultSetDataSource resultSet = gerarDataSource(gerarConsulta(relatorio, this.listaFiltroRelatorioSelecionados));
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, resultSet);
            return print;
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    "Ocorreu um erro ao imprimir o relatório!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void imprimirRelatorio(int relatorioId) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(relatorioId);
        if (relatorio.isPresent()) {
            try {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                ServletOutputStream outputStream = response.getOutputStream();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=" + relatorio.get().getNome() + ".pdf");
                JasperPrint jasperPrint = exportPdfFile(relatorio.get());
                if (jasperPrint == null) {
                    throw new Exception("Erro ao exportar o relatório! (jasperPrint is null)");
                }
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                        "Ocorreu um erro ao imprimir o relatório!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                System.err.println(e.getMessage());
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Relatório não encontrado!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }
}