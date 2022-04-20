/*
@auther Jodie Wilbur
*/

import React from 'react';
import { Link } from 'react-router-dom';
import { useRef, useState, useEffect } from 'react';
// import { faCheck, faTimes, FaInfoCircle } from "@fortawesome/free-solid-svg-icons";
// import { FontAwesomeIcon } from "@forawesome/react-fontawesom";
import '../../App.css'

//<-----Set limits for user name and password----->
const USER_REGEX = /^[a-zA-Z][a-zA-Z0-0_]{3, 23}/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{.8, 24}$/;
const REGISTER_URL = 'register';

const Regester = () => {
const userRef = useReft();      //user input
const errRef = uesRef();         // error alert

//<-----User enters their name on account creation page----->
const [ user, setUser] = useState(''); // Tied to user input.
const [validName, setValidName ] = useState(false); // Verify Name is within the limits
const [ userFocus, setUserFpcus] = useState(false); // Verify Name box is focused
 
    //<-----User enters the first password they want for their account----->
const [ pwd, setPwd] = useState(''); // TIed to user input
const [validPwd, setValidPws ] = useState(false); // Verify Password is withini the limits
const [ pwdFocus, setPwdFocus] = useState(false); // Verify Password box is focused

    //<-----User enters second password. This checks to see if they match----->
const [matchPwd, setMatchPwd] = useState(''); // Seconed password box is selected 
const [validMatch, setValidMatch] =useState(false); // Check of the two password  match
const [matchFocus, setMatchFocus] =useState(false); // Verify second pass box is focused

const [errMsg, setErrMsg] = useState(''); //Display an error if not correct
const [success, setSuccess] = useState(false) // Display that the account had been created.
        
//<-----Autoselects the user name input box on load----->
useEffect(() => {
        userRef.current.focus();
}, [])

//<-----Validate user name----->
useEffect(() => {
        const result = USER_REGEX.test(user);
        console.log(result);
        console.log(user);
        setValidName(result);
}, [user])

//<-----Validate password----->
useEffect(() => {
    const result = PWD_REGEX.test(pwd);
    console.log(result);
    console.log(pwd);
    setValidPws(result);
    const match = pwd === matchPwd;
    setValidMatch(match);
   },   [pwd, matchPwd]);

   //<-----If not valid send error messge----->
useEffect(() => {
        setErrMsg('');
}, [user, pwd, matchPwd])

  return (
    <section>
        <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>;
     </section>
  )
}

export default Regester

// export default function SignUpPage() {

//     return (
//         <div className="text-center m-5-auto">
//             <h2>Join us</h2>
//             <h5>Create your personal account</h5>
//             <form action="/home">
//                 <p>r
//                     <label>Username</label><br/>
//                     <input type="text" name="first_name" required />
//                 </p>
//                 <p>
//                     <label>Email address</label><br/>
//                     <input type="email" name="email" required />
//                 </p>
//                 <p>
//                     <label>Password</label><br/>
//                     <input type="password" name="password" requiredc />
//                 </p>
//                 <p>
//                     <input type="checkbox" name="checkbox" id="checkbox" required /> <span>I agree all statements in <a href="https://google.com" target="_blank" rel="noopener noreferrer">terms of service</a></span>.
//                 </p>
//                 <p>
//                     <button id="sub_btn" type="submit">Register</button>
//                 </p>
//             </form>
//             <footer>
//                 <p><Link to="/">Back to Homepage</Link>.</p>
//             </footer>
//         </div>
//     )
// }