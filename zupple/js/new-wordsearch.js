let newWord = '';
let title = 'untitled';
let width = 10;
let height = 10;
let wordDirections = 5;
let wordCollection = [];

let wordCount = 0;

const apiBaseAddress = 'http://localhost:8080/wordsearch'

const wordSearchDto = {
    title: '',
    width: 20,
    height: 20,
    wordDirections: 1,
    wordCollection: []
};


function newUserWord(event) {
    //newWord = document.getElementById('word').value;
    newWord = event.target.value;
    console.log(event);
    console.log(event.target);
    console.log(newWord);
    document.addEventListener('keypress', (e) => {
        if (e.key === 'Enter' && newWord != '') {
            newWord = '';
            openNextWordField();
        }
    });

}

function openNextWordField() {
    const tmpl = document.getElementById('new-word').content.cloneNode(true);
    const form = document.getElementById('word-list');
    console.log(tmpl);
    console.log(form);
    form.appendChild(tmpl);

    const nextWord = document.querySelector('.next-word');
    nextWord.focus();
    nextWord.classList.remove('next-word');
    
}

function generatePuzzle() {
    wordSearchDto.title = document.getElementById('puzzle-title').value;
    wordSearchDto.wordDirections = document.getElementById('direction-choice').value;
    wordSearchDto.width = document.getElementById('width').value;
    wordSearchDto.height = document.getElementById('height').value;

    const words = document.querySelectorAll('.word');
    for (element of words) {
        if (element.value != '') {
            wordSearchDto.wordCollection.push(element.value);
        }
    }
    
    console.log(wordSearchDto.title);
    console.log(wordSearchDto.width);
    console.log(wordSearchDto.height);
    console.log(wordSearchDto.wordDirections);
    console.log(wordSearchDto.wordCollection);

    const entity = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(wordSearchDto)
    };
    fetch(apiBaseAddress, entity);
        // .then(response => response.json())
        // .then(wordSearch => {
        //     console.log('Success:', wordSearch);
        // });
    return false;
}


document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('list').addEventListener('keypress', event => {
        console.log(event);
        newUserWord(event);
    });

    // document.querySelector('form').addEventListener('focus', event => {
    //     console.log(event);
    //     newUserWord(event);
    // });

    // document.querySelector('form').addEventListener('click', event => {
    //     console.log(event);
    //     newUserWord(event);
    // });

    document.getElementById('generate').addEventListener('click', event => {
        console.log(event);
        generatePuzzle(event);
    });

    // document.addEventListener('keypress', (e) => {
    //     if (e.key === 'Enter') {
    //         // newWord = '';
    //         openNextWordField();
    //     }


});