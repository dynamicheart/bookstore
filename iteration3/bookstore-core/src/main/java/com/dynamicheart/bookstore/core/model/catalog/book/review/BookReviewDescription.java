/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 24, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.dynamicheart.bookstore.core.model.catalog.book.review;


import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_REVIEW_DESCRIPTION", schema = SchemaConstant.BOOKSTORE_SCHEMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
		"BOOK_REVIEW_ID"
	})
})
public class BookReviewDescription extends Description {
	private static final long serialVersionUID = -1957502640742695406L;

	@ManyToOne(targetEntity = BookReview.class)
	@JoinColumn(name="BOOK_REVIEW_ID")
	private BookReview bookReview;

	public BookReviewDescription() {
	}

	public BookReviewDescription(String name) {
		this.setName(name);
	}

	public BookReview getBookReview() {
		return bookReview;
	}

	public void setBookReview(BookReview bookReview) {
		this.bookReview = bookReview;
	}
}
