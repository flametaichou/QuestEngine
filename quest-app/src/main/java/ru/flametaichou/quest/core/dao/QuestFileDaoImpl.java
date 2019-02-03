package ru.flametaichou.quest.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.domain.Scene;

@Repository
public class QuestFileDaoImpl extends HibernateDaoSupport implements QuestFileDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(QuestFile questFile) {
        getHibernateTemplate().saveOrUpdate(questFile);
    }

    @Override
    public QuestFile findById(long id) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<QuestFile> questFiles = new ArrayList<QuestFile>();
            questFiles = sessionFactory.getCurrentSession()
                    .createQuery("from QuestFile p where p.id=?")
                    .setParameter(0, id)
                    .list();

            if (questFiles.size() > 0) {
                return questFiles.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public void delete(QuestFile questFile) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<QuestFile>() {
            @Override
            public QuestFile doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(questFile);
                return null;
            }
        });
    }

    @Override
    public List<QuestFile> listAllQuestFiles() {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<QuestFile> questFiles = new ArrayList<QuestFile>();

            questFiles = sessionFactory.getCurrentSession()
                    .createQuery("from QuestFile")
                    .list();

            return questFiles;
        });
    }

    @Override
    public List<QuestFile> listQuestFiles(Quest quest) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<QuestFile> questFiles = new ArrayList<QuestFile>();

            questFiles = sessionFactory.getCurrentSession()
                    .createQuery("from QuestFile p where p.quest=?")
                    .setParameter(0, quest)
                    .list();

            return questFiles;
        });
    }

    @Override
    public QuestFile findByUniqueCode(UUID uniqueCode) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<QuestFile> questFiles = new ArrayList<QuestFile>();
            questFiles = sessionFactory.getCurrentSession()
                    .createQuery("from QuestFile p where p.uniqueCode=?")
                    .setParameter(0, uniqueCode)
                    .list();

            if (questFiles.size() > 0) {
                return questFiles.get(0);
            } else {
                return null;
            }
        });
    }
}
