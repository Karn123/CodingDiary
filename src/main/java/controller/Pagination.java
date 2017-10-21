package controller;

import cst.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/2/6.
 */
public class Pagination {
    private int currentPageNumber;
    private int totalPageNumber;
    private int pageSize;
    private int pageOffset;
    private int beginNumber;
    private int endNumber;

    public Pagination(int currentPageNumber, int pageSize) {
        this.currentPageNumber = currentPageNumber;
        this.pageSize = pageSize;
    }

    public Pagination(HttpServletRequest request, int pageSize) {
        this.pageSize = pageSize;
        String pageCurrent = (String) request.getParameter("page_current");
        currentPageNumber = 1;
        if (pageCurrent != null) {
            currentPageNumber = Integer.parseInt(pageCurrent);
        }
    }

    public Pagination updateBeginAndEndNumber() {
        if (totalPageNumber < Constants.paginationNumber) {
            beginNumber = 1;
            endNumber = totalPageNumber;
        } else {
            if (currentPageNumber < (Constants.paginationNumber + 1) / 2) {
                beginNumber = 1;
                endNumber = Constants.paginationNumber;
            } else if (currentPageNumber > totalPageNumber - (Constants.paginationNumber - 1) / 2) {
                endNumber = totalPageNumber;
                beginNumber = totalPageNumber - Constants.paginationNumber + 1;
            } else {
                beginNumber = currentPageNumber - (Constants.paginationNumber - 1) / 2;
                endNumber = currentPageNumber + (Constants.paginationNumber - 1) / 2;
            }
        }
        return this;
    }

    public int getBeginNumber() {
        return beginNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public int getOffset() {
        return (currentPageNumber - 1) * pageSize;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalEntryNumber) {
        int totalPageNumber = totalEntryNumber / pageSize;
        if (totalEntryNumber % pageSize != 0) {
            totalPageNumber++;
        }
        this.totalPageNumber = totalPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    @Override
    public String toString() {
        return "currentPageNumber=" + currentPageNumber +
                ", totalPageNumber=" + totalPageNumber +
                ", pageSize=" + pageSize;
    }
}
