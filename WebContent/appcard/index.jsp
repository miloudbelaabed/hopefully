	
	<style>
	.appoint-card {
	    width: 430px;
	    font-size: 22px;
	    background: rgb(255, 255, 255);
	    padding: 20px 30px;
	    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	}
	
	.appoint-card>* {
	    width: 100%;
	}
	
	.card-header {
	    text-align: center;
	    font-size: 28px;
	    padding-bottom: 15px;
	    text-transform: capitalize;
	}
	
	.appoint-card .card-body {}
	
	.appoint-card .card-body .row:first-of-type {
	    border-top: 2px solid rgba(0, 0, 0, 0.14);
	}
	
	.appoint-card .card-body .row {
	    display: flex;
	    padding: 5px 0;
	    border-bottom: 2px solid rgba(0, 0, 0, 0.14);
	    flex-direction: row;
	    justify-content: space-between;
	    text-transform: capitalize;
	}
	
	.appoint-card .card-footer p {
	    font-size: 16px;
	    text-align: center;
	    color: rgba(0, 0, 0, 0.39);
	}
	</style>



    <div class="appoint-card">
        <div class="card-header">
            <div class="log">
                HopeFully
            </div>
        </div>

        <div class="card-body">

            <div class="fullname row">
                <div class="row-key">Full Name :</div>
                <div class="row-value"><%= request.getParameter("fullname") %></div>
            </div>
            <div class="departement row">
                <div class="row-key">Departement :</div>
                <div class="row-value"><%= request.getParameter("dep") %></div>
            </div>
            <div class="appoint-date row">
                <div class="row-key">Date :</div>
                <div class="row-value"><%= request.getParameter("appdate") %></div>
            </div>


            <div class="booked-date row">
                <div class="row-key">Book Date :</div>
                <div class="row-value"><%= request.getParameter("bookdate") %></div>
            </div>

            <div class="payed row">
                <div class="row-key">Payed :</div>
                <div class="row-value">2000DZD</div>
            </div>




        </div>

        <div class="card-footer">
            <div class="log">
                <p>Your satisfaction is our goal and your health is our goal</p>
            </div>
        </div>

    </div>


