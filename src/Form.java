import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

public class Form extends JPanel{

    private List<FormElement> formElements;

    private Form(FormBuilder builder){

        this.formElements = builder.formElements;

        this.setLayout(new GridLayout(this.formElements.size(), 1, 0, 16));
        this.setOpaque(false);

        for (FormElement element : formElements) {

            this.add(element.getLabel());
            this.add(element.getField());

        }
        
    }

    public List<FormElement> getFormElements(){
        return this.formElements;
    }

    public Map<String, String> getData(){

        List<FormElement> formElements = this.getFormElements();
        Map<String, String> data = new HashMap<>();

        for (FormElement element : formElements) {
            
            String key = element.getLabel().getText();
            String value = (String) element.getFieldValue();

            data.put(key, value);

        }
        
        return data;

    }

    public boolean isFormValid(){

        boolean isValid = true;

        // System.out.println("h:" + this.formElements.size());

        Map<String, String> data = getData();

        Set<String> keys = data.keySet();
        Iterator<String> keyIterator = keys.iterator();

        while (keyIterator.hasNext() && isValid) {
            
            String key = keyIterator.next();

            if (data.get(key).isEmpty()) {
                isValid = false;
            }

        }

        return isValid;

    }

    public static class FormBuilder{

        private List<FormElement> formElements;

        public FormBuilder(List<FormElement> formElements) {
            this.formElements = formElements;
        }

        public Form build() {
            return new Form(this);
        }

    }


}
