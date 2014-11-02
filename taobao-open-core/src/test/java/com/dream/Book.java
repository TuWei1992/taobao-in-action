package com.dream;




public class Book {

	 /** The book's ISBN */
	 private String isbn;
	 /** The book's title */
	 private String title;
	 /** The author's name */
	 private String authorName;
	 
	 
	 public Book(){
		 
	 }

	 public Book(String isbn, String title, String authorName) {
	 this.isbn = isbn;
	 this.title = title;
	 this.authorName = authorName;
	 }

	 public String getIsbn() {
	 return isbn;
	 }

	 public void setTitle(String title) {
	 this.title = title;
	 }

	 public String getTitle() {
	 return title;
	 }

	 public void setAuthorName(String authorName) {
	 this.authorName = authorName;
	 }

	 public String getAuthorName() {
	 return authorName;
	 }
	}