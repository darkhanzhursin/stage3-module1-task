import java.util.List;
import java.util.NoSuchElementException;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.factory.Factory;
import com.mjc.school.factory.RepositoryFactory;
import com.mjc.school.repository.Repository;
import com.mjc.school.interfaces.Service;
import com.mjc.school.model.NewsModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsServiceTest {

  private final Service<NewsDtoRequest, NewsDtoResponse> newsService = Factory.getInstance().getNewsService();
  private final Repository<NewsModel> newsRepository = RepositoryFactory.getInstance().getNewsRepository();

  @Test
  void getById() {
    long id = 15;
    NewsDtoResponse serviceResponse = newsService.readById(id);
    assertEquals(id, serviceResponse.id());
  }

  @Test
  void create() {
    NewsDtoRequest request = new NewsDtoRequest(null, "Test title", "Test content", (long) 14);
    NewsDtoResponse serviceResponse = newsService.create(request);
    List<NewsModel> news = newsRepository.readAll();

    NewsModel model = news.get(news.size() - 1);
    assertEquals("Test title", model.getTitle());
    assertEquals("Test content", model.getContent());
    assertNotNull(serviceResponse.lastUpdatedDate());
    assertNotNull(serviceResponse.createDate());
  }

  @Test
  void getAll() {
    List<NewsModel> news = newsRepository.readAll();
    assertEquals(20, news.size());
  }

  @Test
  void update() {
    long id = 5, authorId = 7;
    String title = "Updated title", content = "Updated content";
    NewsDtoRequest newsForUpdate = new NewsDtoRequest(id, title, content, authorId);

    assertNotNull(newsService.update(newsForUpdate));
    NewsModel updatedNews = newsRepository.readById(id);

    assertEquals(title, updatedNews.getTitle());
    assertEquals(content, updatedNews.getContent());
    assertEquals(authorId, updatedNews.getAuthorId());
  }

  @Test
  void delete() {
    long id = 7;
    assertTrue(newsService.deleteById(id));
    assertThrowsExactly(NoSuchElementException.class, () -> newsRepository.readById(id));
  }

}
