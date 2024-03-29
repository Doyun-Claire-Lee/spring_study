package hello.itemservice.domain.item;

import lombok.Data;

//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "가격과 수량의 곱은 10000원 이상이어야 합니다.")
//실무에서 사용하기엔 제약이 많아 Object 오류일 경우 Java 코드로 작성하는 것을 권장
@Data
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;
//    @NotBlank(message = "default error message", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

