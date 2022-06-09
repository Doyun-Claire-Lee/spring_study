package hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HateoasController {

    @GetMapping("/hateoas")
    public HateoasSample hateoas() {
        HateoasSample sample = new HateoasSample();
        sample.setPrefix("prefix");
        sample.setName("name");

        //link 정보 추가하기
        EntityModel<HateoasSample> hateoasSampleResource = EntityModel.of(sample);
        hateoasSampleResource.add(linkTo(methodOn(HateoasController.class).hateoas()).withSelfRel());

        return sample;
    }
}
