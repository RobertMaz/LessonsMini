package controller;


import persist.Category;

import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class CategoryConverter implements Converter<Category> {

    @Inject
    private CategoryRepository categoryRepository;


    @Override
    public Category getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return categoryRepository.findById(Long.parseLong(s))
                .orElseThrow(() -> new ConverterException(new FacesException(String.format("%s is not correct id", s))));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Category category) {
        if (category == null) {
            return "";
        }
        return String.valueOf(category.getId());
    }
}
