$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'event/list',
        datatype: "json",
        colModel: [			
			{ label: 'eventId', name: 'eventId', index: 'event_id', width: 50, key: true },
			{ label: '机构id', name: 'orgId', index: 'org_id', width: 80 }, 			
			{ label: '活动主播id', name: 'hostId', index: 'host_id', width: 80 }, 			
			{ label: '活动标题', name: 'eventTitle', index: 'event_title', width: 80 }, 			
			{ label: '直播类别id', name: 'eventTypeId', index: 'event_type_id', width: 80 }, 			
			{ label: '活动预计开始时间：主要用来配合生成推流过期时间', name: 'eventStartTime', index: 'event_start_time', width: 80 }, 			
			{ label: '直播结束时间，由阿里回调设置', name: 'eventEndTime', index: 'event_end_time', width: 80 }, 			
			{ label: '融云聊天室', name: 'eventDesc', index: 'event_desc', width: 80 }, 			
			{ label: '直播间观看人数：通过查询直播间拉流人数+点播观看人数+自定义', name: 'eventVisitedNum', index: 'event_visited_num', width: 80 }, 			
			{ label: '融云聊天室id', name: 'chatRoomId', index: 'chat_room_id', width: 80 }, 			
			{ label: '直播海报封面', name: 'roomPoster', index: 'room_poster', width: 80 }, 			
			{ label: '直播间状态：0-未开始1-直播中2-结束', name: 'liveStatus', index: 'live_status', width: 80 }, 			
			{ label: '直播间推流地址', name: 'pushUrl', index: 'push_url', width: 80 }, 			
			{ label: '直播间拉流rtmp地址', name: 'liveRtmp', index: 'live_rtmp', width: 80 }, 			
			{ label: '直播间拉流hls地址', name: 'liveHls', index: 'live_hls', width: 80 }, 			
			{ label: '直播间拉流hlv地址', name: 'liveFlv', index: 'live_flv', width: 80 }, 			
			{ label: '录像回看地址', name: 'replayUrl', index: 'replay_url', width: 80 }, 			
			{ label: '记录创建时间', name: 'createTime', index: 'create_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		event: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.event = {};
		},
		update: function (event) {
			var eventId = getSelectedRow();
			if(eventId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(eventId)
		},
		saveOrUpdate: function (event) {
			var url = vm.event.eventId == null ? "event/save" : "event/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.event),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var eventIds = getSelectedRows();
			if(eventIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "event/delete",
                    contentType: "application/json",
				    data: JSON.stringify(eventIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(eventId){
			$.get(baseURL + "event/info/"+eventId, function(r){
                vm.event = r.event;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});