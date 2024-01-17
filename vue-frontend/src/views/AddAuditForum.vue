<template>
    <div class="form, container">
        <div class="form-section">
            <span>Gesetz</span>
            <input type="text" class="disabled" value="PART.CAMO" placeholder="PART.CAMO" v-model="model.l_gesetz" disabled/>
        </div>

        <div class="form-section">
            <span>Type</span>
            <select v-model="model.l_typ" >
                <option>R</option>
                <option>AMC</option>
                <option>GM</option>
            </select>
        </div>

        <div class="form-section">
            <span>Bezeichnung</span>
            <input v-model="model.l_bezeichnung" disabled/>
        </div>

        <div class="form-section">
            <span>Text</span>
            <input v-model="model.l_text"/>
        </div>

        <div class="form-section">
            <span>Gültig Ab</span>
            <input v-model="model.l_gueltigAb"/>
        </div>

        <button type="button" class="btn btn-primary" v-on:click="saveLaw(), $router.push('/law')">Speichern</button>
        <button type="button" class="btn btn-danger" v-on:click="model = {}">Clear</button>
        <RouterView/>
    </div>
</template>

<script>
import axios from "axios";
    export default {
        data() {
            return {
                model: {
                    l_gesetz: "PART.CAMO"
                }
            };
        },
        methods: {
            async saveLaw() {
                try {
                    const response = await axios.post("http://localhost:8080/api/audits/", this.model);
                    // To refresh the handin list in our parent component we can emit an event.
                    this.$emit("addAudit", response.data);
                    this.model = {
                        l_gesetz: "PART.CAMO"
                    };
                }
                catch (e) {
                    console.log(e);
                    if (!e.response) {
                        alert(e.message);
                    }
                }
                
            }
        },
        watch: {
        'model.l_typ': function(newVal) {
            switch(newVal) {
                case 'R':
                    this.model.l_bezeichnung = 'CAMO.A.100';
                    break;
                case 'AMC':
                    this.model.l_bezeichnung = 'AMC1.CAMO.A.100';
                    break;
                case 'GM':
                    this.model.l_bezeichnung = 'GM1.CAMO.A.100';
                    break;
            }
        }
    }
    }
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