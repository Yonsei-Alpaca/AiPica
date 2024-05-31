var group_arr = []
function draw_tags(){
    var parent = document.getElementById("tags");
    var parent_hidden = document.getElementById("tags-hidden");
    parent.textContent = "";
    parent_hidden.textContent = "";
    console.log(group_arr.join(','));

    var tmp_arr = []
    for(var i=0;i<group_arr.length;i++){
        tmp_arr.push(group_arr[i].replaceAll(" ","_"));
    }

    parent_hidden.setAttribute("value", tmp_arr.join(' '));

    for(var i=0; i<group_arr.length;i++){

        var new_div = document.createElement("div");
        parent.innerHTML +=
        '<button class="btn btn-secondary mx-2" onclick="del_group(this)">'+ group_arr[i] +
        '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x"viewBox="0 0 16 16"><path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708" /></svg>' +
        '</button>';
    }
}
function del_group(obj){

    group_arr.splice(group_arr.indexOf(obj.textContent), 1);
    draw_tags();

}
function add_group() {

    var new_group = document.getElementById("group-name");

    if(group_arr.indexOf(new_group.value) < 0 && new_group.value != ''){
        group_arr.push(new_group.value)
        draw_tags();
    }

    new_group.value = "";

}

function replace_space_comma(element){

    var text = element.value;
    element.value = text.replaceAll(",","");
}

function previewImage(event) {
    const preview = document.getElementById('image-preview');
    preview.innerHTML = '';
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function() {
        const img = document.createElement('img');
        img.src = reader.result;
        img.setAttribute("class","col-12");
        preview.appendChild(img);
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.innerHTML = '<span>미리보기 없음</span>';
    }
}
async function genImg() {
    console.log("Gen Image");

    var loadingContainer = document.getElementById('image-preview');
    loadingContainer.innerHTML = '<span>이미지 생성 중...</span>';

    var inputText = document.getElementById('word').innerText;
    console.log(inputText)
    var promptText = prompt("생성할 이미지를 묘사해주세요", inputText);

    var data = {
        "prompt": promptText,
        "n": 1,
        "size": "256x256"
    };

    try {
        const response = await fetch('https://api.openai.com/v1/images/generations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer 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' // 본인의 API 키 입력
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorDetails = await response.json();
            throw new Error(`HTTP error! status: ${response.status}, details: ${JSON.stringify(errorDetails)}`);
        }

        const responseData = await response.json();
        console.log(responseData);
        var imgPreview = document.createElement('img');
        imgPreview.src = responseData.data[0].url;
        imgPreview.style.display = 'block';


        var previewContainer = document.getElementById('image-preview');
        previewContainer.innerHTML = '';
        previewContainer.appendChild(imgPreview);

        document.getElementById("image-url").setAttribute("value", responseData.data[0].url);

    } catch (error) {
        console.error('Error:', error);
        alert('이미지 생성 중 오류가 발생했습니다.');
        var errorContainer = document.getElementById('image-preview');
        errorContainer.innerHTML = '<span>이미지 생성 중 오류 발생</span>';
    }
}

async function generateExampleSentence() {
    var inputWord = document.getElementById('word').value;

    console.log("generating example sentence with "+inputWord);

    var data = {
        "model": "gpt-4",
        "messages": [
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": `Generate a short example sentence using the word "${inputWord}".`}
        ]
    };

    try {
        const response = await fetch('https://api.openai.com/v1/chat/completions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer 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' // 본인의 API 키 입력
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorDetails = await response.json();
            throw new Error(`HTTP error! status: ${response.status}, details: ${JSON.stringify(errorDetails)}`);
        }

        const responseData = await response.json();
        var exampleSentence = responseData.choices[0].message.content.trim();

        // Display the generated example sentence
        document.getElementById("example").setAttribute("value", exampleSentence);

        alert('예문 생성 완료');

    } catch (error) {
        console.error('Error:', error);
        alert('예문 생성 중 오류가 발생했습니다: ' + error.message);
    }
}