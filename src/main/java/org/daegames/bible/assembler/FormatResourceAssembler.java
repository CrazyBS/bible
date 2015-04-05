package org.daegames.bible.assembler;

import org.daegames.bible.controller.FormatController;
import org.daegames.bible.entity.Format;
import org.daegames.bible.resource.FormatResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class FormatResourceAssembler extends ResourceAssemblerSupport<Format, FormatResource> {

    public FormatResourceAssembler() {
        super(FormatController.class, FormatResource.class);
    }

    @Override
    public FormatResource toResource(Format entity) {
        FormatResource resource = createResourceWithId(createPath(entity), entity);

        resource.setFormatId(entity.getFormatId());
        resource.setVersion(entity.getVersion());
        resource.setBook(entity.getBook());
        resource.setChapter(entity.getChapter());
        resource.setStart(entity.getStart());
        resource.setEnd(entity.getEnd());
        resource.setProperties(entity.getProperties());

        return resource;
    }

    private String createPath(Format entity) {
        return entity.getVersion() + "/" + entity.getBook() + "/" + entity.getChapter() + "/" + entity.getFormatId();
    }
}
