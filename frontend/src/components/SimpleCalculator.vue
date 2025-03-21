<template>
  <div class="container">
    <div class="housing">
      <div class="display">
        <p>{{ expression }}</p>
      </div>
      <div class="buttons">
        <button class="calcButton special" @click="clear()" data-testid="clear">c</button>
        <button class="calcButton special" data-testid="ans">ans</button>
        <button class="calcButton special" @click="deleteLast()" data-testid="delete">del</button>
        <button class="calcButton operator" @click="setOperator('+')" data-testid="add">+</button>
        <button class="calcButton number" @click="appendNumber('7')" data-testid="7">7</button>
        <button class="calcButton number" @click="appendNumber('8')" data-testid="8">8</button>
        <button class="calcButton number" @click="appendNumber('9')" data-testid="9">9</button>
        <button class="calcButton operator" @click="setOperator('-')" data-testid="subtract">
          -
        </button>
        <button class="calcButton number" @click="appendNumber('4')" data-testid="4">4</button>
        <button class="calcButton number" @click="appendNumber('5')" data-testid="5">5</button>
        <button class="calcButton number" @click="appendNumber('6')" data-testid="6">6</button>
        <button class="calcButton operator" @click="setOperator('*')" data-testid="multiply">
          *
        </button>
        <button class="calcButton number" @click="appendNumber('1')" data-testid="1">1</button>
        <button class="calcButton number" @click="appendNumber('2')" data-testid="2">2</button>
        <button class="calcButton number" @click="appendNumber('3')" data-testid="3">3</button>
        <button class="calcButton operator" @click="setOperator('/')" data-testid="divide">
          /
        </button>
        <button class="calcButton number" @click="showAlert()" data-testid="help">?</button>
        <button class="calcButton number" @click="appendNumber(0)" data-testid="0">0</button>
        <button class="calcButton number" @click="appendNumber('.')" data-testid="decimal">
          .
        </button>
        <button class="calcButton operator" @click="calculate()" data-testid="equals">=</button>
      </div>
    </div>
    <div class="log">
      <button class="getCalculationsButton" @click="getCalculations">get calculations</button>
      <div class="calculations">
        <ul>
          <li v-for="(calculation, index) in calculations" :key="index" @click="setExpression(calculation.expression)">
            {{ "expression: " + calculation.expression + ", result: " + calculation.result }}
          </li>
        </ul>
      </div>
    </div>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: 'SimpleCalculator',
  data() {
    return {
      expression: '',
      calculations: [],
      errorMessage: ''
    }
  },
  methods: {
    appendNumber(number) {
      this.expression += number
    },
    setOperator(operator) {
      this.expression += ` ${operator} `
    },
    deleteLast() {
      this.expression = this.expression.slice(0, -1)
    },
    clear() {
      this.expression = ''
    },
    async calculate() {
      const token = sessionStorage.getItem('jwtToken');
      try {
        console.log('Calculating expression: ' + this.expression)
        const requestData = { expression: this.expression };
        const response = await axios.post('http://localhost:8080/api/calculator/calculate', requestData, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        this.expression = response.data.result;
      } catch (error) {
        console.error('Error calculating expression:', error);
        this.errorMessage = 'JWT token expired or invalid. Please log in.'
      }
    },
    showAlert() {
      alert('*instruksjoner*')
    },
    async getCalculations() {
      const token = sessionStorage.getItem('jwtToken');
      try {
        console.log('Getting calculations');
        const response = await axios.post('http://localhost:8080/api/calculator/calculations', {},{
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response.data)
        this.calculations = response.data;
      } catch (error) {
        console.error('Error retrieving calculations:', error);
        this.errorMessage = 'JWT token expired or invalid. Please log in.'
      }
    },
    setExpression(expression) {
      this.expression = expression;
    }
  },
}
</script>

<style scoped>


.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 5rem;
}

.housing {
  border: 1px solid var(--color-border);
  border-radius: 4px;
  padding: 2rem;
  background-color: var(--color-background-soft);
}

.calcButton {
  background-color: var(--color-button);
  border: 1px solid var(--color-border);
  border-left: 3px solid var(--color-border);
  border-bottom: 3px solid var(--color-border);
  border-radius: 4px;
  font-size: 2rem;
}

.calcButton:hover {
  border: 2px solid var(--color-border);
}

.special {
  background-color: var(--color-special);
}

.operator {
  background-color: var(--color-operator);
}

.display {
  display: inline-flex;
  align-items: center;
  background-color: var(--color-display-background);
  width: 21.5rem;
  height: 5rem;
  border: 1px solid var(--color-border);
  border-radius: 4px;
  font-size: 3rem;
  margin-bottom: 0.5em;
  padding: 1rem;
  color: var(--color-text);
  overflow: hidden;
  white-space: nowrap;
}

.buttons {
  display: grid;
  grid-template-columns: 5rem 5rem 5rem 5rem;
  grid-template-rows: 5rem 5rem 5rem 5rem 5rem;
  row-gap: 0.5rem;
  column-gap: 0.5rem;
}

.log {
  border: 1px solid var(--color-border);
  border-radius: 4px;
  padding: 2rem;
  background-color: var(--color-background-soft);
  color: var(--color-text);
  width: 24.5rem;

}

.getCalculationsButton {
  width: 100%;
  padding: 0.5rem;
  background-color: hsla(160, 100%, 37%, 1);
  color: var(--vt-c-white);
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.4s;
}

.getCalculationsButton:hover {
  background-color: hsla(160, 100%, 37%, 0.8);
}

.calculations {
  margin-top: 1rem;
  overflow-y: auto;
  max-height: 35.5rem;
}

.calculations ul li {
  padding: 0.5rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.calculations ul li:hover {
  background-color: hsla(160, 100%, 37%, 1);
}

.error {
  background-color: hsla(0, 100%, 75%, 0.6);
  color: var(--color-text);
  margin-top: 1rem;
  padding: 0.5rem;
  border-radius: 4px;
}
</style>
