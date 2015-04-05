package org.daegames.bible.assembler;

import org.daegames.bible.controller.BibleController;
import org.daegames.bible.controller.FormatController;
import org.daegames.bible.entity.Bible;
import org.daegames.bible.resource.BibleResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class BibleResourceAssembler extends ResourceAssemblerSupport<Bible, BibleResource> {

    public BibleResourceAssembler() {
        super(BibleController.class, BibleResource.class);
    }

    @Override
    public BibleResource toResource(Bible bible) {

        BibleResource resource = createResourceWithId(bible.getVerse(), bible);

        resource.setVersion(bible.getVerse().getVersion());
        resource.setBook(bible.getVerse().getBook());
        resource.setChapter(bible.getVerse().getChapter());
        resource.setVerse(bible.getVerse().getVerse());
        resource.setText(bible.getText());
        resource.setHeader(bible.getHeader());
        resource.setProperties(bible.getProperties());

        return resource;
    }
}
