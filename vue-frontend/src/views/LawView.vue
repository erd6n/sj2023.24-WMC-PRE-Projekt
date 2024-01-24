<template>
    <div class = "container">
        <h1>Laws</h1>
        <table>
            <thead>
                <th>Law Id</th>
                <th>Gesetz</th>
                <th>Typ</th>
                <th>Bezeichnung</th>
                <th>text</th>
                <th>Gültig Ab</th>
                <th>
                    <button @click="$router.push('law/addLaw')" type="button" class="btn btn-primary">
                    Add Law
                    </button>
                    <RouterView />
                </th>
            </thead>
            <tbody>
                <tr v-for="law in laws" v-bind:key="law.id" v>
                    <td> {{law.id}} </td>
                    <td> {{law.l_gesetz}} </td>
                    <td> {{law.l_typ}} </td>
                    <td> {{law.l_bezeichnung}} </td>
                    <td> {{law.l_text}} </td>
                    <td> {{law.l_gueltigAb}} </td>
                    <td> 
                        <button type="button" @click="renderEditView(law.id)" class="btn btn-outline-primary">Edit</button>
                        <button type="button" @click="deleteLaw(law.id)" class="btn btn-outline-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import LawService from '../services/LawService'
import axios from "axios";

    export default {
        name: 'Law',
        data(){
            return {
                laws : [],
            }
        },
        methods: {
            async getLaws(){
                this.laws = await LawService.getLaws();
            },
            async deleteLaw(id){
                try {
                    await axios.delete(`http://localhost:8080/api/laws/${id}`);
                    alert(`Law ${id} erfolgreich gelöscht.`);
                    await this.getLaws();
                }
                catch (e) {
                    alert('Fehler beim Löschen des Gesetzes:', JSON.stringify(e));
                }
            },
            async renderEditView(id){
                this.$router.push({name: 'EditLaw', params: {id: id}})
            }
        },
        async created (){
            await this.getLaws()
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

    .container table{
        border-collapse: separate;
        border-spacing: 3vh;
    }

    button {
        margin: 8px;
    }
</style>
