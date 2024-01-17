<template>
  <div class = "container">
        <h1>Audits</h1>
        <table>
            <thead>
                <th>Audit Id</th>
                <th>Audit Datum</th>
                <th>Lead Auditor</th>
                <th>Audit Status</th>
                <th>Thema</th>
                <th>Typ</th>
                <th>
                    <button @click="$router.push('audit/addAudit')" type="button" class="btn btn-primary">
                    Add audit
                    </button>
                    <RouterView/>
                </th>
            </thead>
            <tbody>
                <tr v-for="audit in audits" v-bind:key="audit.id" v>
                    <td> {{audit.id}} </td>
                    <td> {{audit.a_auditDatum}} </td>
                    <td> {{audit.a_leadAuditor}} </td>
                    <td> {{audit.a_auditStatus}} </td>
                    <td> {{audit.a_thema}} </td>
                    <td> {{audit.a_typ}} </td>
                    <td> 
                        <button type="button" @click="renderEditView(audit.id)" class="btn btn-outline-primary">Edit</button>
                        <button type="button" @click="deleteAudit(audit.id)" class="btn btn-outline-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";

    export default {
        name: 'audit',
        data(){
            return {
                audits : [],
            }
        },
        methods: {
            getAudits(){
                axios.get("http://localhost:8080/api/audits/").then((response) => {
                    this.audits = response.data;
                });
            },
            async deleteAudit(id){
                await axios.delete(`http://localhost:8080/api/audits/${id}`)
                .then(response => {
                    alert('Audit erfolgreich gelöscht:', response.data);
                    location.reload();
                })
                .catch(error => {
                    alert('Fehler beim Löschen des Audits:', error);
                });
            },
            async renderEditView(id){
                this.$router.push({name: 'EditAudit', params: {id: id}})
            }
        },
        created (){
            this.getAudits()
        }
    }
</script>


<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.container table {
  border-collapse: separate;
  border-spacing: 3vh;
}
</style>
