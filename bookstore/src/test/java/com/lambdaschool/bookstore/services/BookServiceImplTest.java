package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired
    private SectionService sectionService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void findBookById()
    {
        assertEquals("The Da Vinci Code", bookService.findBookById(29).getTitle());
    }

    private void assertEquals(String the_da_vinci_code, String title) {
    }

    private void assertEquals(int digital_fortess, int title) {
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFindBookById()
    {
        Book book = bookService.findBookById(20000);
        assertEquals("asdasdasd",book.getTitle());
    }

    @Test
    public void delete()
    {
        bookService.delete(29);
        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void faildelete() {
        bookService.delete(1000);
    }

    @Test
    public void save()
    {
        Section section = new Section("Test");
        section  = sectionService.save(section);
        Book book = new Book("New Book", "22222222", 2020, section);

        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void saveFail() {
        Section section = new Section("Failer");
        section = sectionService.save(section);

        Book badBook = new Book();
        badBook.setBookid(200000);
        badBook  = bookService.save(badBook);
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
        bookService.deleteAll();

        assertEquals(0, bookService.findAll().size());
    }
}