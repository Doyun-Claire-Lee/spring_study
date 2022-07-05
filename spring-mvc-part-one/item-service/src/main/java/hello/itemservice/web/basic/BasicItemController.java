package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam int quantity,
                            Model model) {
        Item item = itemRepository.save(new Item(itemName, price, quantity));
        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);

        // @ModelAttribute가 설정되면 자동으로 추가되어 해당 코드는 생략 가능
        // model에 추가되는 이름은 애노테이션 속성값으로 적용
//        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);

        // @ModelAttribute 애노테이션 속성값이 생략될 경우 클래스 이름의 lower camel case로 이름이 지정됨.
//        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    // @ModelAttribute 애노테이션도 생략 가능!
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    // PRG패턴(Post-Redirect-Get)
    // 새로고침시 계속해서 등록되는 현상 방지를 위해 뷰 템플릿을 호출하는 것이 아니라 리다이렉트로 새로운 페이지 요청
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);

        // redirect url을 직접 만들면 위험성이 존재하기 때문에 객체 사용
        redirectAttributes.addAttribute("itemId", savedItem.getId());   //{}로 Url값에 치환됨.
        redirectAttributes.addAttribute("status", true);    // QueryString으로 변환됨.

//        return "redirect:/basic/items/" + item.getId();
        return "redirect:/basic/items/{itemId}";    // -> http://localhost:8080/basic/item/1?status=true
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    // 테스트용 데이터
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
