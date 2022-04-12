import React from "react";

import '../../App.css'

export default function Login(){
    return(
        <body>

            <div>
                <h1>Positivity Jar</h1>
                <p>Today only happens once.  Make it amazing.</p>
            </div>

            <div id="login">
                <div className="pass">
                    <input type="text" value="email" />
                </div>
                <div className="pass">
                    <input type="password" value="password" />
                </div>
                <div class="send">
                    <input type="submit" value="Login" />
                </div>
                <p>
                    <button type="button">Forgot Password?</button>
                </p>
            </div>

        <div class="footer">
            <p>2022 RicePudding</p>
        </div>
    </body>
    )
}