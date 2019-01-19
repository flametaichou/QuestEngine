package ru.flametaichou.quest.core.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.flametaichou.quest.core.domain.Scene;

/**
 * @date 24.08.18
 */
@Repository
public class SceneDaoImpl extends HibernateDaoSupport implements SceneDao {

    @Resource
    public void configure(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveOrUpdate(Scene scene) {
        getHibernateTemplate().saveOrUpdate(scene);
    }

    @Override
    public Scene findById(long id) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Scene> scenes = new ArrayList<Scene>();
            scenes = sessionFactory.getCurrentSession()
                    .createQuery("from Scene scene where scene.id=?")
                    .setParameter(0, id)
                    .list();

            if (scenes.size() > 0) {
                return scenes.get(0);
            } else {
                return null;
            }
        });
    }

    @Override
    public void delete(Scene scene) {
        SessionFactory sessionFactory = this.getSessionFactory();

        getHibernateTemplate().execute(new HibernateCallback<Scene>() {
            @Override
            public Scene doInHibernate(Session session) throws HibernateException {
                sessionFactory.getCurrentSession().delete(scene);
                return null;
            }
        });
    }

    @Override
    public List<Scene> listScenes() {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Scene> scenes = new ArrayList<Scene>();

            scenes = sessionFactory.getCurrentSession()
                    .createQuery("from Scene")
                    .list();

            return scenes;
        });
    }

    @Override
    public Scene findByExternalId(String externalId) {
        SessionFactory sessionFactory = this.getSessionFactory();

        return getHibernateTemplate().execute(session -> {
            List<Scene> scenes = new ArrayList<Scene>();
            scenes = sessionFactory.getCurrentSession()
                    .createQuery("from Scene scene where scene.externalId=?")
                    .setParameter(0, externalId)
                    .list();

            if (scenes.size() > 0) {
                return scenes.get(0);
            } else {
                return null;
            }
        });
    }
}
