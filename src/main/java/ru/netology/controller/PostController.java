package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private static final String DELETE_POST_SUCCESS = "Пост id = %d удалён";
  private final PostService service;
  private final Gson gson = new Gson();

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    final var data = service.all();
    response.setContentType(APPLICATION_JSON);
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    final var data = service.getById(id);
    response.setContentType(APPLICATION_JSON);
    response.getWriter().print(gson.toJson(data));
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.setContentType(APPLICATION_JSON);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    final var data = String.format(DELETE_POST_SUCCESS, id);
    service.removeById(id);
    response.setContentType(APPLICATION_JSON);
    response.getWriter().print(gson.toJson(data));
  }
}
