package tech.tryu.facotrymethod.creator;

import tech.tryu.facotrymethod.product.Button;
import tech.tryu.facotrymethod.product.WebButtion;

/**
 * @author YU
 */
public class WebDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WebButtion();
    }
}
