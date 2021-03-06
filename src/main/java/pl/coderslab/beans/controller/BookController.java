package pl.coderslab.beans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.beans.model.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }
    public BookController(BookService bookService){
        this.bookService = bookService;

    }
    @GetMapping("")
    public @ResponseBody
    List<Book> getList(){
        return bookService.getBooks();
    }
    @PostMapping("")
    public void addBook(@RequestBody Book book){
        bookService.add(book);
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return this.bookService.get(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found");
        });
    }
    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id){
        bookService.delete(id);
    }
    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book){
        bookService.update(book);
    }

}
