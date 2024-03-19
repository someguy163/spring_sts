package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;

	final static public int BOOK_ISBN_ALREADY_EXIST = 0;
	final static public int BOOK_REGISTER_SUCCESS = 1;
	final static public int BOOK_REGISTER_FAIL = -1;


	public int registerBookConfirm(BookVo bookVo) {

		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());

		if (!isISBN) {
			int result = bookDao.insertBook(bookVo);

			if (result >0) {
				return BOOK_REGISTER_SUCCESS;
			}
			else {
				return BOOK_REGISTER_FAIL;
			}
		}
		else {
			return BOOK_ISBN_ALREADY_EXIST;
		}

	}
	
	public List<BookVo> searchBookConfirm(BookVo bookVo){
		
		return bookDao.selectBookBySearch(bookVo);
	}
	
	public BookVo bookDetail(int b_no) {
		return bookDao.selectBook(b_no);
	}
	
	public BookVo modifyBookForm(int b_no) {
		
		return bookDao.selectBook(b_no);
		
	}
	
	public int modifyBookConfirm(BookVo bookVo) {
		return bookDao.updateBook(bookVo);
		
	}
	
	public int deleteBookConfirm(int b_no) {
		
		return bookDao.deleteBook(b_no);
		
	}
}
