<template>
  <div class = "container">
        <h1>Questionss</h1>
        <table>
            <thead>
                <th>Question Id</th>
                <th>Question Law Id</th>
                <th>Lead Auditor</th>
                <th>Audited</th>
                <th>Finding Level</th>
                <th>
                    <button @click="$router.push('questions/addQuestions')" type="button" class="btn btn-primary">
                    Add Question
                    </button>
                    <RouterView/>
                </th>
            </thead>
            <tbody>
                <tr v-for="questions in questionss" v-bind:key="questions.id" v>
                    <td> {{questions.id}} </td>
                    <td> {{questions.a_questionsDatum}} </td>
                    <td> {{questions.a_leadQuestionsor}} </td>
                    <td> {{questions.a_questionsStatus}} </td>
                    <td> {{questions.a_thema}} </td>
                    <td> {{questions.a_typ}} </td>
                    <td> 
                        <button type="button" @click="renderEditView(questions.id)" class="btn btn-outline-primary">Edit</button>
                        <button type="button" @click="deleteQuestions(questions.id)" class="btn btn-outline-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from "axios";

    export default {
        name: 'questions',
        data(){
            return {
                questionss : [],
            }
        },
        methods: {
            getQuestionss(){
                axios.get("http://localhost:8080/api/questions").then((response) => {
                    this.questionss = response.data;
                });
            },
            async deleteQuestions(id){
                await axios.delete(`http://localhost:8080/api/questions/${id}`)
                .then(response => {
                    alert('Questions erfolgreich gelöscht:', response.data);
                    location.reload();
                })
                .catch(error => {
                    alert('Fehler beim Löschen des Questionss:', error);
                });
            },
            async renderEditView(id){
                this.$router.push({name: 'EditQuestions', params: {id: id}})
            }
        },
        created (){
            this.getQuestionss()
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
