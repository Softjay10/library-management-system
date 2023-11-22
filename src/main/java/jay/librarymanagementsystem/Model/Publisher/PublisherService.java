package jay.librarymanagementsystem.Model.Publisher;

import jay.librarymanagementsystem.Exception.NotFoundException;
import jay.librarymanagementsystem.Model.Author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherDao publisherDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Publisher> findAllPublishers() {
        return publisherDao.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Publisher findPublisherById(Long id){
        return publisherDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));
    }


    public void createPublisher(Publisher publisher) {
        publisherDao.save(publisher);
    }


    public void updatePublisher(Publisher publisher) {
        publisherDao.save(publisher);
    }


    public void deletePublisher(Long id) {
        var publisher = publisherDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

        publisherDao.deleteById(publisher.getId());
    }

    public Page<Publisher> findPaginated(Pageable pageable) {

        var pageSize = pageable.getPageSize();
        var currentPage = pageable.getPageNumber();
        var startItem = currentPage * pageSize;
        List<Publisher> list;

        if (findAllPublishers().size() < startItem) {
            list = Collections.emptyList();
        } else {
            var toIndex = Math.min(startItem + pageSize, findAllPublishers().size());
            list = findAllPublishers().subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), findAllPublishers().size());

    }
}
