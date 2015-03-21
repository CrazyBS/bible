package org.daegames.bible.controller;

import org.daegames.bible.assembler.BibleResourceAssembler;
import org.daegames.bible.entity.Bible;
import org.daegames.bible.entity.VerseIdentifier;
import org.daegames.bible.repository.BibleRepository;
import org.daegames.bible.resource.BibleResource;
import org.daegames.bible.resource.ErrorResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.daegames.bible.specification.BibleSpecification.*;
import static org.springframework.data.jpa.domain.Specifications.where;

@RestController
@ExposesResourceFor(Bible.class)
@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class BibleController {

    @Resource
    private BibleRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<BibleResource> getAll(
            @PageableDefault(size = 100) Pageable pageable, PagedResourcesAssembler<Bible> assembler) {
        return assembler.toResource(repository.findAll(pageable), new BibleResourceAssembler());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}")
    public PagedResources<BibleResource> getVersion(
                                        @PageableDefault(size = 100) Pageable pageable,
                                        @PathVariable("version") String version,
                                        PagedResourcesAssembler<Bible> assembler) {
        return assembler.toResource(repository.findAll(isVersion(version), pageable), new BibleResourceAssembler());
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{version}")
//    public List<String> getBibleBooks(@PathVariable("version") String version) {
//        return repository.getAllBooks(version);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}")
    public PagedResources<BibleResource> getBook(@PageableDefault(size = 100) Pageable pageable,
                                                             @PathVariable("version") String version,
                                                             @PathVariable("book") String book,
                                                             PagedResourcesAssembler<Bible> assembler) {
        return assembler.toResource(repository.findAll(where(isVersion(version)).and(isBook(book)), pageable), new BibleResourceAssembler());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}/{chapter:[0-9]$}")
    public HttpEntity<PagedResources<BibleResource>> getChapter(@PageableDefault(size = 100) Pageable pageable,
                                                             @PathVariable("version") String version,
                                                             @PathVariable("book") String book,
                                                             @PathVariable("chapter") Long chapter) {
        Page<Bible> page = repository.findAll(where(isVersion(version)).and(isBook(book)).and(isChapter(chapter)), pageable);
        return new HttpEntity<PagedResources<BibleResource>>(getPagedResource(page));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version}/{book}/{chapter:[0-9]+}.{verse:[0-9]+}")
    public HttpEntity<PagedResources<BibleResource>> getVerse(@PageableDefault(size = 100) Pageable pageable,
                                                              @PathVariable("version") String version,
                                                              @PathVariable("book") String book,
                                                              @PathVariable("chapter") Long chapter,
                                                              @PathVariable("verse") Long verse) {
        Page<Bible> page = repository.findAll(where(isVersion(version)).and(isBook(book)).and(isChapter(chapter)).and(isVerse(verse)), pageable);
        return new HttpEntity<PagedResources<BibleResource>>(getPagedResource(page));
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createVerse(@RequestBody @Valid BibleResource verse, BindingResult results) {
        if(results.hasErrors()) {
            return new ResponseEntity<ErrorResource>(new ErrorResource<BibleResource>(verse, results.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        BibleResourceAssembler assembler = new BibleResourceAssembler();

        Bible requestVerse = new Bible(verse);

        boolean doesExist = repository.exists(requestVerse.getVerse());
        Bible newVerse = repository.save(requestVerse);

        BibleResource newVerseResource = assembler.toResource(newVerse);

        if(doesExist) {
            return new ResponseEntity<BibleResource>(newVerseResource, headers, HttpStatus.OK);
        } else {
            headers.setLocation(ServletUriComponentsBuilder.fromUriString(newVerseResource.getId().getHref()).build().toUri());
            return new ResponseEntity<BibleResource>(newVerseResource, headers, HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{version}/{book}/{chapter:[0-9]+}.{verse:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVerse(@PathVariable("version") String version,
                            @PathVariable("book") String book,
                            @PathVariable("chapter") Long chapter,
                            @PathVariable("verse") Long verse) {
        VerseIdentifier id = new VerseIdentifier();
        id.setVersion(version);
        id.setBook(book);
        id.setChapter(chapter);
        id.setVerse(verse);

        repository.delete(id);
    }

    private PagedResources<BibleResource> getPagedResource(Page<Bible> page) {
        BibleResourceAssembler assembler = new BibleResourceAssembler();

        List<BibleResource> resources = assembler.toResources(page.getContent());

        PagedResources.PageMetadata metaData = new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources<BibleResource>(resources, metaData);
    }
}
