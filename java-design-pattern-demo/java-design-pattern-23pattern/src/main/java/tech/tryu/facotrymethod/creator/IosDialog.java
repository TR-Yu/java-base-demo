package tech.tryu.facotrymethod.creator;

import tech.tryu.facotrymethod.product.Button;
import tech.tryu.facotrymethod.product.IosButton;

/**
 * @author YU
 */
public class IosDialog extends Dialog {

    @Override
    public Button createButton() {
        return new IosButton();
    }
}
