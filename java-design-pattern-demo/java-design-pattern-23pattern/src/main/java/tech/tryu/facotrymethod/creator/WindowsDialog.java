package tech.tryu.facotrymethod.creator;

import tech.tryu.facotrymethod.product.Button;
import tech.tryu.facotrymethod.product.WindowButtion;

/**
 * @author YU
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowButtion();
    }

}
