package accounter.model;



import accounter.AccounterPageBean;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//@FacesConverter(forClass=Card.class)
@FacesConverter("accounter.model.CardConverter")
public class CardConverter implements Converter {

    public CardConverter() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {


//        if(value.equals("Bronze")) //value.equals("accounter.model.BronzeCard"
//        {
//
//            return new BronzeCard();
//
//
//
//        } else if (value.equals("Gold")){
//
//            return new GoldCard();
//        }
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{accounterPageBean}", AccounterPageBean.class);

        AccounterPageBean cards = (AccounterPageBean) vex.getValue(context.getELContext());
        return cards.getCARD(value);


//return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

//        if(value instanceof BronzeCard) {
//
//
//
//            return String.valueOf(((BronzeCard) value).name);
//
//            //return  value.toString();
//        } else {
//            //return value.toString();
//            return String.valueOf(((GoldCard) value).name);
//        }
        return (((Card) value).name);


    }
}
