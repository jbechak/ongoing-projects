let newWord = '';
let title = 'untitled';
let width = 2;
let height = 2;
let wordDirections = 5;
let wordCollection = [];
let wordCount = 0;
let longest = 2;
let difficulty = '';

let puzzlesFetched = false;

const apiBaseAddress = 'http://localhost:8080/wordsearch'

const wordSearchDto = {
    title: '',
    width: 20,
    height: 20,
    wordDirections: 1,
    wordCollection: []
};

const wordSearch = {
    wordSearchId: '',
    title: '',
    gridString: '',
    width: 20,
    height: 20,
    wordCollection: [],
    wordDirections: 1,
    wordCount: 0,
    difficulty: '',
    genre: '',
    instructions: '',
    description: '',
    creator: '',
    htmlPath: ''
}

function createWordCollection() {
    wordSearchDto.wordCollection = [];
    const words = document.querySelectorAll('.word');
    for (element of words) {
        if (element.value != '') {
            wordSearchDto.wordCollection.push(element.value);
        }
    }
}

function updateWordCount() {
    createWordCollection();
    wordCount = wordSearchDto.wordCollection.length;
    console.log(wordCount);
    document.getElementById('current-count').innerText = 'Word Count: ' + wordCount;
}

function updateDimensions() {
    if (width < longest + 1) {
        width = longest + 1;
        document.querySelector('#x-axis input').value = width;  
    }
    if (height < longest + 1) {
        height = longest + 1;
        document.querySelector('#y-axis input').value = height; 
    }
    document.querySelector('#x-axis input').min = longest + 1;
    document.querySelector('#y-axis input').min = longest + 1;
}

function updateDifficulty() {
    if (getCredits() >= 8) {
        difficulty = "Level 5 - Very Difficult";
    }
    else if (getCredits() >= 6) {
        difficulty = "Level 4 - Difficult";
    }
    else if (getCredits() >= 3) {
        difficulty = "Level 3 - Medium";
    }
    else if (getCredits() >= 1) {
        difficulty = "Level 2 - Easy";
    }
    else difficulty = "Level 1 - Very Easy";

    document.querySelector('#difficulty > h2').innerText = difficulty;
}

function getCredits() {
    let credits = 0;
    
    credits += remainingSpaces() / 50;
    credits += document.getElementById('direction-choice').value * 2 - 2;
    credits += (wordCount - 1) / 10;
    return credits;
}

function remainingSpaces() {
    let totalSpaces = width * height;
    let totalLetters = 0;
    wordSearchDto.wordCollection.forEach(word => totalLetters += word.length);
    return totalSpaces - totalLetters;
}

function longestWord() {
    longest = 2;
    for (word of wordSearchDto.wordCollection) {
        word = word.replace(/\s+/g, '');
        if (word.length > longest) {
            longest = word.length;
            console.log(word);
            console.log(longest);
        }
    }
}





function newUserWord(event) {
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
    updateWordCount();
    longestWord();
    updateDimensions();
    updateDifficulty();
}



function newPuzzle(event) {
    event.preventDefault()
    const config = document.getElementById('config');
    const list = document.getElementById('list');
    config.classList.add('on-screen');
    list.classList.add('on-screen');

    showPuzzleCreator();
    hidePuzzleOpener();
}

function getAll() {
    if (!puzzlesFetched) {
        fetch(apiBaseAddress)
            .then(response => response.json())
            .then(puzzleList => {
                console.log(puzzleList);
                puzzleList.forEach(puzzle => {
                    console.log(puzzle.title);
                    console.log('hi');
                    const tmpl = document.getElementById('table-row').content.cloneNode(true);
                    tmpl.getElementById('id').innerText = puzzle.wordSearchId;
                    tmpl.getElementById('titl').innerText = puzzle.title;
                    tmpl.getElementById('diff').innerText = puzzle.difficulty;
                    tmpl.getElementById('wd').innerText = puzzle.wordDirections;
                    tmpl.getElementById('wid').innerText = puzzle.width;
                    tmpl.getElementById('hei').innerText = puzzle.height;
                    tmpl.getElementById('wc').innerText = puzzle.wordCount;
                    tmpl.getElementById('gen').innerText = puzzle.genre;
                    const id = 'p' + puzzle.wordSearchId;
                    tmpl.querySelector('tr').id = id;

                    const puzzles = document.getElementById('puzzles');
                    puzzles.appendChild(tmpl);

                    document.getElementById(id).addEventListener('click', event => {
                        console.log(event);
                        getPuzzle(event, puzzle.wordSearchId);
                    })
                });
            }); 
    }
    
    hidePuzzleCreator();
    showPuzzleOpener();

    const puzzles = document.getElementById('puzzles');
    puzzles.classList.add('on-screen');
    puzzlesFetched = true;
}



function getPuzzle(event, id) {
    console.log(id);
    clearPuzzle();
    fetch(apiBaseAddress + '/' + id)
        .then(response => response.json())
        .then(puzzle => {
            wordSearch.wordSearchId = puzzle.wordSearchId;
            wordSearch.title = puzzle.title;
            wordSearch.gridString = puzzle.gridString;
            wordSearch.width = puzzle.width;
            wordSearch.height = puzzle.height;
            wordSearch.wordCollection = puzzle.wordCollection;
            wordSearch.wordDirections = puzzle.wordDirections;
            wordSearch.wordCount = puzzle.wordCount;
            wordSearch.difficulty = puzzle.difficulty;
            wordSearch.genre = puzzle.genre;
            wordSearch.instructions = puzzle.instructions;
            wordSearch.description = puzzle.description;
            wordSearch.creator = puzzle.creator;
            wordSearch.htmlPath = puzzle.htmlPath;
            console.log(wordSearch);
            hidePuzzleOpener();
            printWordSearch();
        })
}

function clearPuzzle() {
    const puzzle = document.getElementById('puzzle');
    puzzle.textContent = ''; 
    const wordList = document.getElementById('puzzle-list');
    wordList.textContent = '';
}

function showPuzzleCreator() {
    const puzzleCreator = document.getElementById('puzzle-creator');
    puzzleCreator.classList.remove('display-none');
    puzzleCreator.classList.add('display-flex');
    document.getElementById('generate').classList.add('display-inline', 'fade-in-button');
    document.getElementById('reset').classList.add('display-inline', 'fade-in-button');

}

function showPuzzleOpener() {
    const puzzleOpener = document.getElementById('puzzle-opener');
    puzzleOpener.classList.remove('display-none');
    puzzleOpener.classList.add('display-inline');
}

function hidePuzzleCreator() {
    const puzzleCreator = document.getElementById('puzzle-creator');
    puzzleCreator.classList.remove('display-flex');
    puzzleCreator.classList.add('display-none');
    document.getElementById('generate').classList.remove('display-inline');
    document.getElementById('generate').classList.add('display-none');
    document.getElementById('reset').classList.remove('display-inline');
    document.getElementById('reset').classList.add('display-none');
}

function hidePuzzleOpener() {
    const puzzleOpener = document.getElementById('puzzle-opener');
    puzzleOpener.classList.remove('display-inline');
    puzzleOpener.classList.add('display-none');
}

function printWordSearch() {
    const wordSearchRows = wordSearch.gridString.split('\n');
    
    console.log(wordSearchRows);
    
    let tmpl = document.getElementById('print-puzzle').content.cloneNode(true);
    tmpl.getElementById('print-link').href = wordSearch.htmlPath;
    tmpl.getElementById('puzzle-name').innerText = wordSearch.title;
    tmpl.getElementById('puzzle-level').innerText = wordSearch.difficulty;
    tmpl.getElementById('puzzle-instructions').innerText = wordSearch.instructions;
    const wordSearchTable = tmpl.getElementById('puzzle-table')
    let fontSize = 'medium-letters';
    if (wordSearch.width <= 10) {
        fontSize = 'big-letters';
    } else if (wordSearch.width > 20) {
            fontSize = 'small-letters';
    }

    wordSearchRows.forEach(row => {
        const rowArray = row.split('');
        console.log(rowArray.join(' '));
        rowWithSpaces = rowArray.join(' ');

        const newRow = document.getElementById('row').content.cloneNode(true);
        newRow.getElementById('puzzle-row').innerText = rowWithSpaces;
        newRow.getElementById('puzzle-row').classList.add(fontSize);
        wordSearchTable.appendChild(newRow);
    });
    document.getElementById('puzzle').appendChild(tmpl);
    console.log(tmpl);
   
    tmpl = document.getElementById('list-of-words').content.cloneNode(true);
    wordSearch.wordCollection.forEach(word => {
        tempWord = document.getElementById('temp-word').content.cloneNode(true);
        tempWord.querySelector('.puzzle-word').innerText = word;
        tmpl.appendChild(tempWord);
    })

    document.getElementById('puzzle-list').appendChild(tmpl);
    console.log(tmpl);
}



function resetPuzzle() {
    const entries = document.querySelectorAll('.word-entry');
    entries.forEach(entry => entry.textContent = '');
    document.querySelector('.first-word').value = '';
    document.getElementById('puzzle-title').value = '';
    document.getElementById('direction-choice').value = '1';
    width = 1;
    height = 1;
    longest = 2;
    updateWordCount();
    updateDimensions();
}

function generatePuzzle(event) {
    event.preventDefault();
    wordSearchDto.title = document.getElementById('puzzle-title').value;
    wordSearchDto.wordDirections = document.getElementById('direction-choice').value;
    wordSearchDto.width = document.getElementById('width').value;
    wordSearchDto.height = document.getElementById('height').value;
    createWordCollection();
    
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
        
    
}


document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('list').addEventListener('keypress', event => {
        console.log(event);
        newUserWord(event);
    });


    document.getElementById('create').addEventListener('click', event => {
        console.log(event);
        newPuzzle(event);
    });

    document.getElementById('open').addEventListener('click', event => {
        console.log(event);
        getAll(event);
    });

    document.getElementById('generate').addEventListener('click', generatePuzzle);
    document.getElementById('reset').addEventListener('click', resetPuzzle);
    document.getElementById('word-directions').addEventListener('click', updateDifficulty);



});