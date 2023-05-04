<template>
    <div class=" container jumbotron">
        <h1>About Us</h1>
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h1>About to Your Farm Metric Application</h1>
                        <p>With this application, you can record metrics from devices on your farm, query them, set up
                            alerts, and add
                            devices as needed.</p>
                        <p>Farmers have always relied on their experience and intuition to manage their farms. However, as
                            farming practices
                            become more complex and the demand for food increases, the need for accurate and up-to-date data
                            is becoming
                            increasingly important. By recording metrics such as weather patterns, crop yields, and
                            livestock health, farmers
                            can gain insights into the performance of their farm and make data-driven decisions about how to
                            improve
                            efficiency and productivity. These metrics can also be used to track progress over time and
                            identify areas for
                            improvement. Additionally, by setting up alerts for their farm, farmers can be notified of
                            potential issues before
                            they become major problems. For example, if there is a sudden drop in temperature, an alert can
                            be sent to the
                            farmer to take action to protect their crops or livestock. By being proactive and staying
                            informed, farmers can
                            minimize risk and maximize their yields.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h1>Connect with Developer</h1>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <font-awesome-icon icon="fab fa-linkedin fa-5x"></font-awesome-icon>
                                        <h5 class="card-title">LinkedIn</h5>
                                        <p class="card-text">Connect with LinkedIn</p>
                                        <a href="https://www.linkedin.com/in/tannar-moss-558a651b0/"
                                            class="btn btn-primary">View
                                            Profile</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <font-awesome-icon icon="fas fa-envelope fa-5x"></font-awesome-icon>
                                        <h5 class="card-title">Email</h5>
                                        <p class="card-text">Connect with Email</p>
                                        <a href="mailto:tannar.moss@gmail.com" class="btn btn-primary  float-bottum">Send
                                            Email</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <font-awesome-icon icon="fab fa-github fa-5x"></font-awesome-icon>
                                        <h5 class="card-title">GitHub</h5>
                                        <p class="card-text">View projects on GitHub</p>
                                        <a href="https://github.com/mossT8" class="btn btn-primary">View Projects</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss">
.jumbotron {
    .row, .card, .card-body {
        z-index: 0;
    }
}
.card {
    height: 100%;
}
</style>
  
<script>
export default {
    name: "about-page",
};
</script>
  