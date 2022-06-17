package com.mycompany.bookservice.controller;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.entity.BookEntity;
import com.mycompany.bookservice.repository.BookRepository;
import com.mycompany.bookservice.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("success scenario for adding a book")
    void testAddBook(){
BookDTO book=new BookDTO();

book.setBookId(1L);
book.setName("dummy book1");

BookDTO savedBook= new BookDTO();
savedBook.setBookId(1L);
savedBook.setName("dummy book1");
        Mockito.when(bookService.addBook(book)).thenReturn(savedBook);
   ResponseEntity<BookDTO> responseEntity =  bookController.addBook(book);
Assertions.assertNotNull(responseEntity.getBody().getBookId());
         // Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
       Assertions.assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue());
  }


@Test
@DisplayName("success scenario for get all book")
void testGetAll(){
List<BookDTO> bookDTOList=new ArrayList<>();

BookDTO bookDTO=new BookDTO();
bookDTO.setBookId(1L);
bookDTO.setName("dummy");
bookDTOList.add(bookDTO);

   Mockito.when(bookService.getAllBook()).thenReturn(bookDTOList);
   ResponseEntity<List<BookDTO>> responseEntity =  bookController.getAllBook();
Assertions.assertNotNull(responseEntity.getBody().size());
Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
    }


    @Test
    @DisplayName("success scenario for update book price")
    void testUpdateBookPrice(){

       BookDTO dto = new BookDTO();
        dto.setPricePerQty(99.05);

        Mockito.when(bookService.updateBookPrice(Mockito.any(),Mockito.anyLong())).thenReturn(dto);
        ResponseEntity<BookDTO> responseEntity = bookController.updateBookPrice(dto,1L);
      //expected 99.05 value Actual value=99.05
        assertEquals(99.05, responseEntity.getBody().getPricePerQty());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName(" success Scenario get book")
    void testGetBook() {
        List<BookDTO> bookList = new ArrayList<>();
        BookDTO dto = new BookDTO();
        dto.setBookId(1L);

        dto.setName("Dummy getallBook");
        bookList.add(dto);

        when(bookService.getAllBook()).thenReturn(bookList);
        ResponseEntity<List<BookDTO>> responseEntity = bookController.getAllBook();
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

    }

    @Test
    void testUpdateBook(){

        BookDTO bookDTO=new BookDTO(); //create a obj insert to list then do call when mawthod calll then returns what it returns
        bookDTO.setBookId(2L);
        bookDTO.setName("dummy");
        bookDTO.setPricePerQty(21.21);
        bookDTO.setAuthorName("war");
        bookDTO.setDescription("god of war");
        List<BookDTO> bookDTOS=new ArrayList<>();
        bookDTOS.add(bookDTO);

        when(bookService.updateBook(bookDTO,2L)).thenReturn(bookDTO);
        ResponseEntity<BookDTO> responseEntity  =bookController.updateBook(bookDTO,2l);
        assertEquals(2,responseEntity.getBody().getBookId());
        assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

    }
    /*@Test
void testDeleteBook(){

    BookDTO bookDTO=new BookDTO();
    bookDTO.setBookId(2l);
    bookDTO.setName("dummy");

    }*/
    @Test
void testGetOneBook(){
        BookDTO dto=new BookDTO();
        dto.setBookId(3l);
        dto.setName("call of duty");
        List<BookDTO> list=new ArrayList<>();
        list.add(dto);
    when(bookService.getBook(3l)).thenReturn(dto);
       ResponseEntity<BookDTO> responseEntity= bookController.getBook(3l);
        assertEquals(3l,responseEntity.getBody().getBookId());
        
    }

}
