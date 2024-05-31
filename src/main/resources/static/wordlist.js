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
