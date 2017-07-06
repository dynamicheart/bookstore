package com.dynamicheart.bookstore.store.tags;

import com.dynamicheart.bookstore.store.constants.Constants;
import com.dynamicheart.bookstore.store.model.store.Breadcrumb;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dynamicheart on 7/6/2017.
 */
public class StoreBreadcrumbsTag extends TagSupport {

    private static final long serialVersionUID = -6960927153262731568L;

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreBreadcrumbsTag.class);

    private Long categoryId = null;

    private Long bookId = null;


    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext
                    .getRequest();
            Breadcrumb breadCrumb = (Breadcrumb)request.getAttribute(Constants.BREADCRUMB);

            StringBuilder ref = new StringBuilder();

            if(breadCrumb!=null && !StringUtils.isBlank(breadCrumb.getUrlRefContent())) {
                ref.append(Constants.SLASH).append(Constants.REF).append(Constants.EQUALS).append(breadCrumb.getUrlRefContent());
                if(categoryId!=null) {
                    List<String> ids = this.parseBreadCrumb(breadCrumb.getUrlRefContent());
                    if(!ids.contains(String.valueOf(this.getCategoryId()))) {
                        ref.append(",").append(this.getCategoryId().longValue());
                    }
                }
            } else {
                if(categoryId!=null) {
                    ref.append(Constants.SLASH).append(Constants.REF).append(Constants.EQUALS).append(Constants.REF_C).append(this.getCategoryId());
                } else {
                    ref.append("");
                }
            }
            pageContext.getOut().print(ref.toString());
        } catch (Exception ex) {
            LOGGER.error("Error while getting content url", ex);
        }
        return SKIP_BODY;
    }

    /** only category **/
    private List<String> parseBreadCrumb(String refContent) throws Exception {

        /** c:1,2,3 **/
        String[] categoryComa = refContent.split(":");
        String[] categoryIds = categoryComa[1].split(",");
        return new LinkedList(Arrays.asList(categoryIds));

    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
