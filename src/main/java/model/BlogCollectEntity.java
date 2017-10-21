package model;

import javax.persistence.*;

/**
 * Created by Karn on 2017/2/14.
 */
@Entity
@Table(name = "blog_collect", schema = "CodingDiaryDB", catalog = "")
@IdClass(BlogCollectEntityPK.class)
public class BlogCollectEntity {
    private int collectBlogId;
    private int collectorId;
    private BlogEntity blogByCollectBlogId;
    private UserinfoEntity userinfoByCollectorId;
    private TfEntity tfByIsCollected;

    @Id
    @Column(name = "collectBlogID", nullable = false)
    public int getCollectBlogId() {
        return collectBlogId;
    }

    public void setCollectBlogId(int collectBlogId) {
        this.collectBlogId = collectBlogId;
    }

    @Id
    @Column(name = "collectorID", nullable = false)
    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCollectEntity that = (BlogCollectEntity) o;

        if (collectBlogId != that.collectBlogId) return false;
        if (collectorId != that.collectorId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = collectBlogId;
        result = 31 * result + collectorId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "collectBlogID", referencedColumnName = "blogID", nullable = false)
    public BlogEntity getBlogByCollectBlogId() {
        return blogByCollectBlogId;
    }

    public void setBlogByCollectBlogId(BlogEntity blogByCollectBlogId) {
        this.blogByCollectBlogId = blogByCollectBlogId;
    }

    @ManyToOne
    @JoinColumn(name = "collectorID", referencedColumnName = "userID", nullable = false)
    public UserinfoEntity getUserinfoByCollectorId() {
        return userinfoByCollectorId;
    }

    public void setUserinfoByCollectorId(UserinfoEntity userinfoByCollectorId) {
        this.userinfoByCollectorId = userinfoByCollectorId;
    }

    @ManyToOne
    @JoinColumn(name = "isCollected", referencedColumnName = "TFID")
    public TfEntity getTfByIsCollected() {
        return tfByIsCollected;
    }

    public void setTfByIsCollected(TfEntity tfByIsCollected) {
        this.tfByIsCollected = tfByIsCollected;
    }
}
