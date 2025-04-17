package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Button {

    public void click(SelenideElement selenideElement) {
        selenideElement.shouldBe(Condition.visible).click();
    }
}
