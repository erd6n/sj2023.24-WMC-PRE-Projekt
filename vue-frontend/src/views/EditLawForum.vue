<template>
    <div class="form, container">
         <div class="form-section">
            <span>Gesetz</span>
            <input type="text" placeholder="PART.CAMO" v-model="law.l_gesetz" disabled/>
            <span v-if="v$.law.l_gesetz.$error">{{ v$.law.l_gesetz.$errors[0].$message }}</span>
        </div>

        <div class="form-section">
            <span>Type</span>
            <select v-model="law.l_typ">
                <option v-for="type in types" v-bind:key="type" v-bind:value="type">
                    {{ type }}
                </option>  
            </select>
            <span v-if="v$.law.l_typ.$error">{{ v$.law.l_typ.$errors[0].$message }}</span>
        </div>

        <div class="form-section">
            <span>Bezeichnung</span>
            <input v-model="law.l_bezeichnung" disabled/>
            <span v-if="v$.law.l_bezeichnung.$error">{{ v$.law.l_bezeichnung.$errors[0].$message }}</span>
        </div>

        <div class="form-section">
            <span>Text</span>
            <input v-model="law.l_text"/>
            <span v-if="v$.law.l_text.$error">{{ v$.law.l_text.$errors[0].$message }}</span>
        </div>

        <div class="form-section">
            <input type="date" v-model="law.l_gueltigAb">
        </div>

        <button type="button" class="btn btn-primary" v-on:click="updateLaw()">Speichern</button>
        <RouterView/>
    </div>
</template>

<script>
import axios from "axios";
import useValidate from '@vuelidate/core'
import { required, email, minLength, sameAs } from '@vuelidate/validators'
export default {
    data() {
        return {
            v$: useValidate(),    
            law: {
                l_gesetz: '',
                l_typ: '',
                l_bezeichnung: '',
                l_text: '',
                l_gueltigAb: ''
            },
            types: []
        };
    },
    async created() {
        const response = await axios.get(`http://localhost:8080/api/laws/${this.$route.params.id}`);
        this.law = response.data;
    },
    async mounted() {
        const response = await axios.get("http://localhost:8080/api/laws/types")
        this.types = response.data
    },
    validations() {
        return {
            law: {
                l_gesetz: { required },
                l_typ: { required },
                l_bezeichnung: { required },
                l_text: { required },
                l_gueltigAb: { required }
            }
        }
    }, 
    methods: {
        async updateLaw() {
            this.v$.$validate()
            if (!this.v$.$error) {
                const response = await axios.put(`http://localhost:8080/api/laws/${this.law.id}`, this.law);
                this.law = response.data;
                this.$router.push('/law');
            }
        }
    },
    watch: {
        'law.l_typ': function(newVal) {
            switch(newVal) {
                case 'R':
                    this.law.l_bezeichnung = 'CAMO.A.100';
                    break;
                case 'AMC':
                    this.law.l_bezeichnung = 'AMC1.CAMO.A.100';
                    break;
                case 'GM':
                    this.law.l_bezeichnung = 'GM1.CAMO.A.100';
                    break;
            }
        }
    }
};
</script>

<style>
    .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    
    }

    .container table {
    border-collapse: separate;
    border-spacing: 3vh;
    }

    .form-section {
        margin: 1vh
    }
</style>