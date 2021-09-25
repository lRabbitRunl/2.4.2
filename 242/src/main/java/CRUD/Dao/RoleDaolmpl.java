package CRUD.Dao;

import CRUD.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Repository("roleDao")
public class RoleDaolmpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;
    @Override
    public void setRole(Role role) {
        em.persist(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getAllRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
