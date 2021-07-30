const express = require('express')
const mongoose = require('mongoose')
const path = require('path')
const ejs = require('ejs');
const Coupon = require('./models/coupon')

const dbUrl =  "mongodb://localhost:27017/coupon";
mongoose.connect(dbUrl, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology: true
})

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error"));
db.once("open", () => { console.log("Connected db") });

const app = express();
app.set("view engine", "ejs");
app.use(express.static(__dirname + '/public'));
app.use("/static", express.static('./static'));
app.use(express.json())
app.use(express.urlencoded({extended: true}));

app.get('/', (req, res) => {
    res.render('home');
});
app.get('/contact', (req, res) => {
    res.render('contact');
});
app.get('/buy', async(req, res) => {
    let coupon = await Coupon.find({});
    res.render('buy',{coupon});
});
app.post('/buy', async(req, res) => {
    console.log(req.body)
    var coupon = new Coupon(req.body);
    await coupon.save();
    res.redirect('/buy');
});
app.get('/about', (req, res) => {
    res.render('about');
});
app.get('/sale', (req, res) => {
    res.render('sale');
});
app.get('/yourb', (req, res) => {
    res.render('yourb');
});
app.get('/yours', (req, res) => {
    res.render('yours');
});
app.get('/login', (req, res) => {
    res.render('login');
});

const port1 = 5000;

app.listen(port1, () => console.log(`Port ${port1}`));

