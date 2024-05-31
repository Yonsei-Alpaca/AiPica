function speak(text, opt_prop) {
   window.speechSynthesis.cancel(); // 현재 읽고있다면 초기화

   const prop = opt_prop || {};

   const speechMsg = new SpeechSynthesisUtterance();
   speechMsg.rate = prop.rate || 1; // 속도: 0.1 ~ 10
   speechMsg.pitch = prop.pitch || 1; // 음높이: 0 ~ 2
   speechMsg.lang = prop.lang || "en-US";
   speechMsg.text = text;

   // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
   window.speechSynthesis.speak(speechMsg);
}
function playAudio(event, element) {
   event.stopPropagation();
   const wordElement = element.closest('.word-item').querySelector('.word');
   const word = wordElement ? wordElement.textContent : '';
   speak(word, {
       rate: 1,
       pitch: 1.2,
       lang: "en-US"
   });
}
function update_bookmark(){
    const form = document.createElement('form'); // form 태그 생성
    form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
    form.setAttribute('action', url); // 전송할 url 지정

    const data_1 = document.createElement('input'); // input 태그 생성
    data_1.setAttribute('type', 'hidden'); // type = hidden
    data_1.setAttribute('name', 'data1'); // 데이터의 key
    data_1.setAttribute('value', data1); // 데이터의 value (여기서는 data1)

    // 여러 개의 데이터를 보낼 경우 위와 같은 코드 반복
    const data_2 = document.createElement('input');
    data_2.setAttribute('type', 'hidden');
    data_2.setAttribute('name', 'data2');
    data_2.setAttribute('value', data2);

    // form 태그에 input 태그 넣고 summit 가능하게 처리
    form.appendChild(data_1);
    form.appendChild(data_2);

    document.body.appendChild(form);

    form.submit();
}

function update_group(){
    var selectTag = document.getElementById('select-group');

    var value = selectTag.options[selectTag.selectedIndex].value;

    var cards = document.querySelectorAll('.card-body');

    for(let i=0; i < cards.length; i++)  {
      cards[i].parentElement.setAttribute("style", "display:none;");
    }

    if(value=="그룹없음") value = "card-body";

    cards = document.querySelectorAll('.'+value+'');

    for(let i=0; i < cards.length; i++)  {
      cards[i].parentElement.setAttribute("style", "");
    }

}

group_arr = ["그룹없음"];
window.addEventListener("load", function(event) {

    var x = document.getElementsByClassName('card-body');

    for (var i = 0; i < x.length; i++){
        var classes = x[i].className.split(" ");
        for(var j=0;j<classes.length;j++){
            if(classes[j] == "card-body")   continue;
            else if(classes[j] == "null")        continue;
            else if(!(group_arr.indexOf(classes[j]) >= 0))
                group_arr.push(classes[j]);
        }
    }

    console.log(group_arr);


    var selectTag = document.getElementById("select-group");
    var option_text = "";

    for (var i = 0; i < group_arr.length; i++)
        option_text += '<option id="select-group" value="'+group_arr[i]+'">'+group_arr[i].replace('_', ' ')+'</option>';
    selectTag.innerHTML = option_text;

});
