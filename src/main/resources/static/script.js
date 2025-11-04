const URL = "https://emailscheduler-production-c3d0.up.railway.app/emails";

const sendEmail = async () => {
    const mail= {
        email: document.getElementById("email").value,
        subject: document.getElementById("subject").value,
        message: document.getElementById("message").value,
        status: "PENDING",
        sechedaLocalDateTime: document.getElementById("scheduleTime").value
    }
    try {
        const response = await fetch(`${URL}/post`,{
            method:"POST",
            headers: { "Content-Type": "application/json" },
            body:JSON.stringify(mail)
        });
        if (response.ok) {
            alert("message sent seccufully")
        }else{
            alert("failed to sent")
        }
            
        
    } catch (error) {
        console.log("not been sent",error);
        
    }
} 
const getemail = async () => {
  try {
    const res = await fetch(`${URL}/allmail`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });

    const mailContainer = document.getElementById("mail");
    mailContainer.innerHTML = "<p class='no-mail'>Fetching mails...</p>";

    if (res.ok) {
      const data = await res.json();
      console.log(data);

      mailContainer.innerHTML = ""; // Clear old content

      if (data.length === 0) {
        mailContainer.innerHTML = "<p class='no-mail'>No mails found.</p>";
        return;
      }

      // Loop through and style each mail
      data.forEach((ele, index) => {
        const card = document.createElement("div");
        card.classList.add("mail-card");
        card.style.animation = `slideIn 0.4s ease ${(index * 0.1).toFixed(1)}s forwards`;
        card.innerHTML = `
          <div class="mail-header">
            <span>#${ele.id}</span>
            <span>${ele.subject || "No Subject"}</span>
          </div>
          <div class="mail-body">
            <strong>To:</strong> ${ele.email}<br>
            <strong>Message:</strong> ${ele.message || "No message"}<br>
            <strong>Status:</strong> ${ele.status || "Unknown"}
          </div>
          <div class="mail-meta">📅 Logged at ${new Date().toLocaleString()}</div>
        `;
        mailContainer.appendChild(card);
      });
    } else {
      throw new Error("Data not found from server");
    }
  } catch (error) {
    console.error("Fetch Error:", error);
    const mailContainer = document.getElementById("mail");
    mailContainer.innerHTML = `<p class='no-mail'>⚠️ Error fetching mails</p>`;
  }
};


const deletemaill = async () =>{
    const id = document.getElementById("num").value;
    try{
        const deleteMsg = await fetch(`${URL}/${id}`,{
            method:"DELETE"
        });
        const msg = await deleteMsg.text();
        alert(msg);
    }
    catch(error){
        console.log("the error is",error);
        
    }
}
const themes = [
  { class: "theme-agni", name: "Agni", symbol: "🜂" },
  { class: "theme-varuna", name: "Varuna", symbol: "🜄" },
  { class: "theme-vayu", name: "Vayu", symbol: "🜁" },
  { class: "theme-prithvi", name: "Prithvi", symbol: "🜃" },
  { class: "theme-akasha", name: "Akasha", symbol: "🜀" }
];

let current = 0;

document.getElementById("themeToggle").addEventListener("click", () => {
  document.body.classList.remove(themes[current].class);
  current = (current + 1) % themes.length;
  document.body.classList.add(themes[current].class);
  document.getElementById("themeToggle").innerText = `${themes[current].symbol} Theme: ${themes[current].name}`;
});



