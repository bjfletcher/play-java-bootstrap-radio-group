package controllers;

import play.*;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application extends Controller {

    List<String> answers = Arrays.asList("Answer 1", "Answer 2", "Answer 3");

    public static class Answer {

        @Constraints.Required
        protected String answer;

        public Answer() {
            this("");
        }

        public Answer(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    Form<Answer> answerForm = Form.form(Answer.class);

    public Result index() {
        return ok(index.render(answers, answerForm));
    }

    public Result post() {
        Form<Answer> result = answerForm.bindFromRequest();
        if (result.hasErrors()) {
            return badRequest(index.render(answers, result));
        } else {
            Answer data = result.get();
            return ok("You chose answer: " + data.answer);
        }
    }

}
