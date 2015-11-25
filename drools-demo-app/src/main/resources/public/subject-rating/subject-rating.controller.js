(function() {
    
    var SubjectRatingController = function ($scope,$http) {
        $scope.hasCourse = false;
        $scope.addSubject = false;
        $scope.rated = false;
        $scope.hasNotSelectedSubject = true;
        $scope.subjectRatingList = [];
        $scope.courseList = [];
        $scope.subjectList = [];
        var subjectRatingCount = 0;
        var ratedSubjects = {};

        $http.get("/subject/list")
            .success(function(response) {
                $scope.subjectList = response.subjects;
                createSubjectRatingSubjects();
            });
        $scope.rateAnotherSubject = rateAnotherSubject;
        $scope.showAdviseCourse = showAdviseCourse;

        function rateAnotherSubject(){
            saveSubjectRating();
            subjectRatingCount += 1;
            createSubjectRatingSubjects();
        }
        function showAdviseCourse(){
            saveSubjectRating();
            $http({
                method: "GET",
                url : "/course/advice",
                params : ratedSubjects
            })
                .success(function(response) {
                    $scope.hasCourse = true;
                    $scope.courseList = response.courseDTOList;
                });
        }
        function saveSubjectRating(){
            var select = document.getElementById("subject_"+ subjectRatingCount)
            var subject = select.options[select.selectedIndex].value;
            var rating = document.querySelector('input[name="radioName_'+subjectRatingCount+'"]:checked').value;
            ratedSubjects[subject] = rating;

        }
        function createSubjectRatingSubjects(){
            var element = document.getElementById("subject_content");

            var hr = document.createElement("hr");
            var div = document.createElement("div");
            div.className = 'row';

            var htmlStr = '<fieldset id="rating_'+subjectRatingCount+'"><div class="col-md-4">' +
                '<select id="subject_'+subjectRatingCount+'" class="form-control">' ;

            for(var i = 0; i < $scope.subjectList.length; i++  ){
                htmlStr += '<option value="'+$scope.subjectList[i].code+'">'+$scope.subjectList[i].name+'</option>' ;
            }
            htmlStr +=      '</select></div>'+
                '<div class="col-md-8" >' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="0" checked>0' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="1">1' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="2">2' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="3">3' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="4">4' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="5">5' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="6">6' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="7">7' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="8">8' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="9">9' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="10">10' +

                '</div>' +
                '</fieldset>';
            div.innerHTML = htmlStr;
            element.appendChild(div);
            element.appendChild(hr);
        }

	};

    SubjectRatingController.$inject = ['$scope','$http'];

    angular.module('ruleApp.subjectRatingApp')
      .controller('SubjectRatingController', SubjectRatingController);

}());

