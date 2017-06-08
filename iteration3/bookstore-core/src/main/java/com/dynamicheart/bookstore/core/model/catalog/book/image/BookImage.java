package com.dynamicheart.bookstore.core.model.catalog.book.image;

import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;
import java.io.InputStream;

/**
 * Created by dynamicheart on 6/2/2017.
 */

@Document(collection = "BOOK_IMAGE")
public class BookImage extends BookstoreEntity<ObjectId, BookImage>{
    
    private static final long serialVersionUID = 2776268357880333124L;

    @Id
    private ObjectId id;

    @Field("BOOK_IMAGE")
    private String bookImage;

    @Field("DEFAULT_IMAGE")
    private boolean defaultImage = true;

    /**
     * default to 0 for images managed by the system
     */
    @Field("IMAGE_TYPE")
    private int imageType;

    /**
     * Refers to images not accessible through the system. It may also be a video.
     */
    @Field("BOOK_IMAGE_URL")
    private String bookImageUrl;

    @Field("IMAGE_CONTENT")
    private Byte[] imageContent;

    @Field("IMAGE_CROP")
    private boolean imageCrop;

    @Field("BOOK_ID")
    @Indexed(unique = true)
    private Long bookId;

    @Transient
    private InputStream image = null;

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public boolean isDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public Byte[] getImageContent() {
        return imageContent;
    }

    public void setImageContent(Byte[] imageContent) {
        this.imageContent = imageContent;
    }

    public boolean isImageCrop() {
        return imageCrop;
    }

    public void setImageCrop(boolean imageCrop) {
        this.imageCrop = imageCrop;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
