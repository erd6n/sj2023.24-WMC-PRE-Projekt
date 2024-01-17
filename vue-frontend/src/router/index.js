import { createRouter, createWebHistory } from 'vue-router'
import AuditView from '../views/AuditView.vue'
import AddLawForum from '../views/AddLawForum.vue';
import LawView from '../views/LawView.vue'
import EditLaw from '../views/EditLawForum.vue'
import QuestionsView from '../views/QuestionsView.vue'
import AddAuditForum from '../views/AddAuditForum.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'audits',
      component: AuditView
    },
    {
      path: '/law',
      name: 'law',
      component: LawView
      //component: () => import('../views/LawView.vue')
    },
    {
      path: '/law/addLaw',
      name: 'addLaw',
      component: AddLawForum
    },
    { 
      path: '/law/edit/:id', 
      name: 'EditLaw', 
      component: EditLaw, 
      props: true 
    },
    {
      path: '/questions',
      name: 'questions',
      component: QuestionsView
    },
    {
      path: '/audit/addAudit',
      name: 'addAudit',
      component: AddAuditForum
    }
  ]
})

export default router
