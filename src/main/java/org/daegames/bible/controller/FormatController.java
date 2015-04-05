package org.daegames.bible.controller;

import org.daegames.bible.assembler.FormatResourceAssembler;
import org.daegames.bible.entity.Bible;
import org.daegames.bible.entity.Format;
import org.daegames.bible.repository.FormatRepository;
import org.daegames.bible.resource.BibleResource;
import org.daegames.bible.resource.FormatResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;

@RestController
@ExposesResourceFor(Format.class)
@RequestMapping(value = "/format", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormatController {

    @Resource
    private FormatRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<FormatResource> getAll(
            @PageableDefault(size = 100) Pageable pageable, PagedResourcesAssembler<Format> assembler) {
        Page<Format> page = repository.findAll(pageable);
        return assembler.toResource(page, new FormatResourceAssembler());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}/{chapter:[0-9]$}")
    public PagedResources<FormatResource> getFormatForChapter(@PageableDefault(size = 100) Pageable pageable,
                                                   @PathVariable("version") String version,
                                                   @PathVariable("book") String book,
                                                   @PathVariable("chapter") Long chapter,
                                                   PagedResourcesAssembler<Format> assembler) {
        // TODO: Add specification
        return assembler.toResource(repository.findAll(pageable), new FormatResourceAssembler());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}/{chapter:[0-9]+}.{verse:[0-9]+}")
    public FormatResource getFormatForVerse(@PageableDefault(size = 100) Pageable pageable,
                                                            @PathVariable("version") String version,
                                                            @PathVariable("book") String book,
                                                            @PathVariable("chapter") Long chapter,
                                                            @PathVariable("verse") Long verse) {
        FormatResourceAssembler assembler = new FormatResourceAssembler();
        // TODO: Add specification
        Format format = repository.findOne((long) 21);
        if(null == format) {
            throw new ResourceNotFound();
        }
        return assembler.toResource(format);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}/{chapter:[0-9]+}/{formatId}")
    public FormatResource getFormat(@PageableDefault(size = 100) Pageable pageable,
                                                   @PathVariable("version") String version,
                                                   @PathVariable("book") String book,
                                                   @PathVariable("chapter") Long chapter,
                                                   @PathVariable("formatId") Long formatId) {
        FormatResourceAssembler assembler = new FormatResourceAssembler();
        Format format = repository.findOne(formatId);
        if(null == format) {
            throw new ResourceNotFound();
        }
        return assembler.toResource(format);
    }

    // TODO: Add add/delete mapping

    @ExceptionHandler(value = ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFoundHandler() {}

    private class ResourceNotFound extends RuntimeException {
    }
}
