/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;

@FacesConverter(value = "EstadoConservacaoConverter")

public class EstadoConservacaoConverter implements Converter<Object> {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (EstadoConservacao) uiComponent.getAttributes().get(value);
        }
        return null;
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof EstadoConservacao) {
            EstadoConservacao entity = (EstadoConservacao) value;
            if (entity != null && entity instanceof EstadoConservacao && entity.getId() != null) {
                uiComponent.getAttributes().put(entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}