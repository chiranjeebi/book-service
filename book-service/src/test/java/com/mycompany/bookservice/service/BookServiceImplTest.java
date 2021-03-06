
package com.mycompany.bookservice.service;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.entity.BookEntity;
import com.mycompany.bookservice.repository.BookRepository;
import com.mycompany.bookservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void addBookTest(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);

        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(1L);

        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        BookDTO book= bookService.addBook(bookDTO);
        Assertions.assertEquals(1L,book.getBookId());


    }

    @Test
    void updateBookPriceTest(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);

        BookDTO bookDTO=new BookDTO();
        bookDTO.setPricePerQty(99.99);

        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(bookEntity));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);

        bookDTO=bookService.updateBookPrice(bookDTO,1L);
        Assertions.assertEquals(1L,bookDTO.getBookId());

    }

    @Test
    void getAllBookTest(){
        List<BookDTO> bookDTOList=new ArrayList<>();
        BookDTO bookDtO=new BookDTO();
        bookDTOList.add(bookDtO);
        BookEntity bookEntity=new BookEntity();
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        bookDTOList=bookService.getAllBook();
        Assertions.assertEquals(1,bookDTOList.size());

    }
    @Test
    void getBookTest() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(1L);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(bookEntity));
        BookDTO bookDTO = bookService.getBook(1L);
        Assertions.assertEquals(1L, bookDTO.getBookId());
    }

/*    @Test
void testUpdateBookQty(){
List<BookEntity> be= new ArrayList<>();
BookDTO  dto=new BookDTO();
dto.setName("war");
dto.setBookId(9l);

        Mockito.when(bookRepository.findAll()).thenReturn(be);
        BookDTO bookDTO =bookService.updateBookQty(9l);
        Assertions.assertEquals(9l,dto.getBookId());
}*/

}
