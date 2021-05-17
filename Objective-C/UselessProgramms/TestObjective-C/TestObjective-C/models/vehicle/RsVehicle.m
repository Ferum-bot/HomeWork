//
//  RsVehicle.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RsVehicle.h"

@interface RsVehicle ()

@property (nonatomic, strong, readwrite) RSPassenger* captain;
@property (nonatomic, assign, readwrite) BOOL running;

@end

@implementation RsVehicle
    
- (instancetype)initWithPassenger:(RSPassenger *)captain {
    self = [self init];
    if (self) {
        self.captain = captain;
    }
    return self;
}
    
- (void)toggleEngine:(BOOL)running {
    self.running = running;
    NSLog(self.running ? @"Engine is running" : @"Engine is stopped");
}

@end
