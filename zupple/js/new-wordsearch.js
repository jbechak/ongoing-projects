let newWord = '';

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

    // let newDiv = document.createElement('div');
    // newDiv.classList.add('new-word');
    // const lastWord = document.getElementById('last-word');
    // lastWord.insertAdjacentElement('beforebegin', newDiv);

    // const label = document.createElement('label');
    // label.setAttribute('for', 'word');
    // label.innerText = 'word';
    // newDiv = document.querySelector('.new-word');
    // newDiv.appendChild(label);

    // const input = document.createElement('input');
    // input.setAttribute('type', 'text');
    // input.classList.add('word');
    // input.setAttribute('placeholder', 'Enter a word');
    // newDiv.appendChild(input);

    // newDiv.classList.add('word');
    // newDiv.classList.remove('new-word');
    
}


document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('list').addEventListener('keypress', event => {
        console.log(event);
        newUserWord(event);
    });

    document.querySelector('form').addEventListener('focus', event => {
        console.log(event);
        newUserWord(event);
    });

    document.querySelector('form').addEventListener('click', event => {
        console.log(event);
        newUserWord(event);
    });

    // document.addEventListener('keypress', (e) => {
    //     if (e.key === 'Enter') {
    //         // newWord = '';
    //         openNextWordField();
    //     }


});